package com.example.films_native;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;


public class MainActivity extends AppCompatActivity {

    private Button verPeliculas;
    private Button agregarPeliculas;
    private ImageView logoImageView;
    private Spinner languageSpinner;
    private TextView titleTextView;
    private TextView addMovieButton;
    private TextView viewMoviesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar vistas
        verPeliculas = findViewById(R.id.view_movies_button);
        agregarPeliculas = findViewById(R.id.add_movie_button);
        logoImageView = findViewById(R.id.logo_image_view);
        languageSpinner = findViewById(R.id.language_spinner);
        titleTextView = findViewById(R.id.title);
        addMovieButton = findViewById(R.id.add_movie_button);
        viewMoviesButton = findViewById(R.id.view_movies_button);

        String selectedLanguage = languageSpinner.getSelectedItem().toString();


        // Configurar el Spinner para cambiar el idioma
        languageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedLanguage = parent.getItemAtPosition(position).toString();
                switch (selectedLanguage) {
                    case "Español":
                        titleTextView.setText("Películas vistas");
                        addMovieButton.setText("Agregar Película");
                        viewMoviesButton.setText("Ver Películas");
                        break;
                    case "Inglés":
                        // Cambiar las etiquetas al inglés
                        titleTextView.setText("Viewed Movies");
                        addMovieButton.setText("Add Movie");
                        viewMoviesButton.setText("View Movies");
                        break;
                    case "Alemán":
                        // Cambiar las etiquetas al alemán
                        titleTextView.setText("Gesehene Filme");
                        addMovieButton.setText("Film hinzufügen");
                        viewMoviesButton.setText("Filme anzeigen");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // Establecer onClickListeners para los botones
        agregarPeliculas.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AgregarPeliculasActivity.class);
            intent.putExtra("languageIndex", languageSpinner.getSelectedItemPosition());
            startActivity(intent);
        });

        verPeliculas.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, VerPeliculasActivity.class);
            intent.putExtra("languageIndex", languageSpinner.getSelectedItemPosition());
            startActivity(intent);
        });

        // Cargar imagen desde assets
        try {
            InputStream inputStream = getAssets().open("logo.png");
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            logoImageView.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
