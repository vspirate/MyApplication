package com.example.myapplication.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

public class ImageDownloader{
     public static Bitmap downloadImagesByUrl(String... urls) { //фоновий потік
        String url = urls [ 0 ];
        Bitmap mIcon = null ;
        try {
            InputStream in = new java.net.URL(url).openStream();
            mIcon = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e( "Error" , e.getMessage());
        }
        return mIcon;
    }
}
