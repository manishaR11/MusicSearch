package com.musicsearch.core;


import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.musicsearch.DO.MusicDataDO;
import com.musicsearch.DO.MusicSearchDO;
import com.musicsearch.R;
import com.musicsearch.constants.MusicSearchConstants;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class MusicSearchFrgament extends Fragment implements View.OnClickListener {
    private EditText searchText = null;
    private Button searchButton = null;
    private String searchTerm = null;
    private TableLayout musicTableLayout = null;
    private TextView trackName = null;
    private TextView artistName = null;
    private ImageView albumImage = null;
    private MusicDataDO[] musicDO = null;
    private JSONObject json = null;
    private MusicSearchDO musicArray = null;
    private View rootView = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_music_search, container, false);
        searchText = (EditText) rootView.findViewById(R.id.searchText);
        searchButton = (Button) rootView.findViewById(R.id.search_button);
        searchButton.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        searchTerm = String.valueOf(searchText.getText());
        String urlString = MusicSearchConstants.ITUNES_API_URL;
        if (null != searchTerm) {
            MusicSearchHelper helper = new MusicSearchHelper(getActivity(), rootView);
            //Appending user entered string to Itunes api url
            helper.execute(urlString + searchTerm);
        } else {
            Toast.makeText(getActivity(), MusicSearchConstants.SEARCH_ERROR, Toast.LENGTH_LONG).show();
        }
    }
}

//Async class to perform the http connections
class MusicSearchHelper extends AsyncTask<String, String, String> {
    private String result = "";
    private Context mContext;
    private StringBuilder builder;
    private ProgressDialog pDialog = null;
    private JSONObject json = null;
    private View view;
    MusicSearchDO musicArray = null;

    //Constructor To get the activity context and the root view
    public MusicSearchHelper(Context context, View rootView) {
        mContext = context;
        view = rootView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //  Showing progress dialog
        pDialog = new ProgressDialog(mContext);
        pDialog.setMessage("Searching Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();

    }

    @Override
    protected String doInBackground(String... params) {
        String urlString = params[0]; // Getting the URL to call
        String resultToDisplay = "";
        InputStream in = null;
        builder = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(params[0]);
        // HTTP Get
        try {

            try {
                HttpResponse response = client.execute(httpGet);
                StatusLine statusLine = response.getStatusLine();
                int statusCode = statusLine.getStatusCode();
                if (statusCode == 200) {//Success Response Code
                    HttpEntity entity = response.getEntity();
                    String result = EntityUtils.toString(entity);
                    json = new JSONObject(result);
                    Log.d("RESULT", result.toString());
                } else {
                    Log.e("", "Request failed");
                    Toast.makeText(mContext, "Services unavailable please try again later", Toast.LENGTH_LONG).show();
                }
            } catch (ClientProtocolException e) {
                e.printStackTrace();
                Toast.makeText(mContext, "Services unavailable please try again later", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(mContext, "Services unavailable please try again later", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(mContext, "Services unavailable please try again later", Toast.LENGTH_LONG).show();
        }
        return result;
    }

    protected void onPostExecute(String result) {
        int i = 0;
        if (null != json) {
            Gson gson = new Gson();
            //converting the json object to MusicSearchDO object
            musicArray = gson.fromJson(json.toString(), MusicSearchDO.class);

            //Populating the table with
            TableLayout musicTableLayout = (TableLayout) view.findViewById(R.id.search_table_layout);

            for (i = 0; i < musicArray.getResults().length; i++) {

                {
                    final TableRow tr1 = new TableRow(mContext);
                    tr1.setLayoutParams(new ViewGroup.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                    // Inflating the row and filling the feilds with MusicSearchDO
                    LinearLayout linearLayout = new LinearLayout(mContext);
                    linearLayout.setOrientation(LinearLayout.VERTICAL);
                    LinearLayout.LayoutParams textViewParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    ImageView albumImage = new ImageView(mContext);
                    TextView trackName = new TextView(mContext);
                    TextView artistName = new TextView(mContext);
                    URL newurl = null;
                    try {
                        newurl = new URL(musicArray.getResults()[i].getArtworkUrl60().toString());
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    //downloading the image and setting as a bitmap to imageview
                    albumImage.setImageBitmap(SearchHelper.getBitmap(newurl));
                    //Assigning the row values for each feild
                    artistName.setText(musicArray.getResults()[i].artistName);
                    trackName.setText(musicArray.getResults()[i].trackName);
                    linearLayout.addView(albumImage);
                    linearLayout.addView(artistName);
                    linearLayout.addView(trackName);
                    tr1.addView(linearLayout);
                    final int finalI = i;
                    tr1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tr1.setBackgroundColor(mContext.getResources().getColor(R.color.button_material_light));
                            //Launching AlbumDetailsActivity on click of the table row item
                            Intent intent = new Intent(mContext, AlbumDetailsActivity.class);
                            intent.putExtra("Artist Name", musicArray.getResults()[finalI].artistName);
                            intent.putExtra("Album URL", musicArray.getResults()[finalI].getArtworkUrl60().toString());
                            intent.putExtra("Track Name", musicArray.getResults()[finalI].trackName);
                            mContext.startActivity(intent);
                        }
                    });

                    musicTableLayout.setColumnShrinkable(i, true);
                    musicTableLayout.addView(tr1, new ViewGroup.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
                }
                musicTableLayout.requestLayout();
            }
        }

        pDialog.dismiss();
    }
}