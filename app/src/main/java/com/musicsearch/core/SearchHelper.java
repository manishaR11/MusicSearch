package com.musicsearch.core;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Manisha on 7/22/2015.
 */
public class SearchHelper {
    //Download the image from the url provided and return the bitmap image
    public static Bitmap getBitmap(URL newurl) {
        Bitmap albumImg = null;
        try {
            HttpURLConnection connection = (HttpURLConnection) newurl.openConnection();
            connection.setDoInput(true);
            try {
                connection.connect();
            } catch (IOException e) {
                e.printStackTrace();
            }
            InputStream input = connection.getInputStream();
            albumImg = BitmapFactory.decodeStream(input);
            Log.e("Bitmap", "returned");
            return albumImg;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}

