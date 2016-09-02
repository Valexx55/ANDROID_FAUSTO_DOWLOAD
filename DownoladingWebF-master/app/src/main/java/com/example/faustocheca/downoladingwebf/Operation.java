package com.example.faustocheca.downoladingwebf;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by faustocheca on 1/9/16.
 */
public class Operation extends AsyncTask<String, Void, Bitmap> {

    private long timeDifference;
    private Bitmap bitmap;
    private long startTime;
    private  HttpURLConnection connection;
    private Context context;

    public Operation(Context context) {
        this.context = context;
    }


    public long getTimeDifference() {
        return timeDifference;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {

        startTime = SystemClock.currentThreadTimeMillis();



        try {
            URL url = new URL(strings[0]);
            connection = (HttpURLConnection) url.openConnection();
            InputStream input = connection.getInputStream();
            input.available();
            Log.d(">>TAG2",""+input.available());
            bitmap = BitmapFactory.decodeStream(input);
            timeDifference = (SystemClock.currentThreadTimeMillis()-startTime);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;


    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        MainActivity mainActivity = (MainActivity) context;
        mainActivity.dibujarResultados(bitmap,timeDifference);
    }
}