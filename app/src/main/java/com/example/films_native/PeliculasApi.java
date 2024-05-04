package com.example.films_native;

import android.os.AsyncTask;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class PeliculasApi extends AsyncTask<String, Void, Pelicula> {
    private PeliculaCallback callback;

    public PeliculasApi(PeliculaCallback callback) {
        this.callback = callback;
    }

    @Override
    protected Pelicula doInBackground(String... urls) {
        String jsonResponse = null;
        try {
            URL url = new URL(urls[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                InputStream in = urlConnection.getInputStream();
                Scanner scanner = new Scanner(in);
                scanner.useDelimiter("\\A");
                if (scanner.hasNext()) {
                    jsonResponse = scanner.next();
                }
            } finally {
                urlConnection.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (jsonResponse != null) {
            return Pelicula.getJsonResponse(jsonResponse);
        } else {
            return null;
        }
    }

    @Override
    protected void onPostExecute(Pelicula pelicula) {
        if (pelicula != null) {
            callback.onPeliculaLoaded(pelicula);
        } else {
            // Handle error case
        }
    }
}
