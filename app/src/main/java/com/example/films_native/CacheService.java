package com.example.films_native;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CacheService {
    public static List<Pelicula> getFilmsFromCache(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String filmsJson = sharedPreferences.getString("peliculas", "");
        Log.d("Peliculas de cache", "Peliculas: " + filmsJson);
        Type type = new TypeToken<List<Pelicula>>(){}.getType();
        try {
            List<Pelicula> peliculas = new Gson().fromJson(filmsJson, type);
            return peliculas != null ? peliculas : new ArrayList<>();

        } catch (JsonSyntaxException e) {
            return new ArrayList<>();
        }
    }

    public static void saveFilmsToCache(Context context, List<Pelicula> peliculas) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String filmsJson = new Gson().toJson(peliculas);
        Log.d("Guardar Peliculas a la cache", "Peliculas JSON: " + filmsJson);
        editor.putString("peliculas", filmsJson);
        editor.apply();
    }



}
