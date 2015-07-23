package com.musicsearch.core;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

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
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;


public class AlbumDetailsActivity extends ActionBarActivity {
    private String artistNameBundle = null;
    private String albumURL = null;
    private URL url = null;
    private String trackNameBundle = null;
    private TextView artistName = null;
    private ImageView trackImage = null;
    private TextView trackname = null;
    public TextView lyricsText = null;
    private ScrollView lyrics = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_details);
        Bundle bundle = getIntent().getExtras();
        artistNameBundle = bundle.getString("Artist Name");//getExtra("Artist Name");
        trackNameBundle = bundle.getString("Track Name");
        artistName = (TextView) this.findViewById(R.id.artistName);
        trackImage = (ImageView) this.findViewById(R.id.albumImage);
        trackname = (TextView) this.findViewById(R.id.albumName);

        artistName.setText("Artist: " + artistNameBundle);
        trackname.setText("Track: " + trackNameBundle);
        albumURL = bundle.getString("Album URL");
        try {
            trackImage.setImageBitmap(SearchHelper.getBitmap(new URL(albumURL)));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        if (artistName != null && trackNameBundle != null) {
            String newartistName = artistNameBundle.replaceAll("\\s", "+");
            String newtrackName = trackNameBundle.replaceAll("\\s", "+");
            View view = this.findViewById(android.R.id.content);
            LyricsSearchHelper helper = new LyricsSearchHelper(this, view);
            String url = MusicSearchConstants.LYRICS_API_URL + newartistName + "&song=" + newtrackName + "&fmt=json";
            Log.d("AlbumDetailsActivity URL", url);
            helper.execute(url);
        }
    }
}

//Async class to perform the http connections
class LyricsSearchHelper extends AsyncTask<String, String, String> {
    String result = "";
    private Context mContext;
    private StringBuilder builder;
    private ProgressDialog pDialog = null;
    JSONObject json = null;
    View view = null;
    MusicSearchDO musicArray = null;
    //Constructor To get the activity context

    public LyricsSearchHelper(Context context, View view) {
        mContext = context;
        this.view = view;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //  Showing progress dialog
        pDialog = new ProgressDialog(mContext);
        pDialog.setMessage("Searching for Lyrics...");
        pDialog.setCancelable(false);
        pDialog.show();

    }

    @Override
    protected String doInBackground(String... params) {
        String urlString = params[0]; // URL to call
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
                    result = EntityUtils.toString(entity);

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
        }
        return result;
    }

    protected void onPostExecute(String result) {
        String lyrics = null;
        Log.d("LYRICS RESULT", result.toString());
        String temp = null;
        try {
            String object = (String) new JSONTokener(result).nextValue();
            temp = result.substring(result.indexOf("lyrics"), result.indexOf("url"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        TextView lyricsText = (TextView) view.findViewById(R.id.lyrics_text);
        lyricsText.setText(temp.replaceAll("'", ""));
        pDialog.dismiss();
    }

}
