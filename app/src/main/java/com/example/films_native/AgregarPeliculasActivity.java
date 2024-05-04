package com.example.films_native;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AgregarPeliculasActivity extends AppCompatActivity implements PeliculaCallback {

    String apiKey = "983f896e";
    String url = "https://www.omdbapi.com/?apikey=" + apiKey + "&t=";

    private String selectedLanguage;
    private Pelicula pelicula;
    private EditText titulo;
    private Button guardar;
    private ImageView logoImageView;
    private TextView dateLabel;
    private EditText dateEditText;
    private TextView cityLabel;
    private EditText cityEditText;
    private Button buscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregar_pelicula);

        buscar = findViewById(R.id.search);
        guardar = findViewById(R.id.save);
        logoImageView = findViewById(R.id.logo_image_view);
        dateLabel = findViewById(R.id.date_label);
        dateEditText = findViewById(R.id.date);
        cityLabel = findViewById(R.id.city_label);
        cityEditText = findViewById(R.id.city);

        // Traducción de los textos
        String[] movieTitleTexts = {
                "Título de la película",
                "Movie Title",
                "Filmtitel"
        };
        String[] dateLabelTexts = {
                "Fecha de vista",
                "Date of viewing",
                "Datum der Ansicht"
        };
        String[] cityLabelTexts = {
                "Ciudad",
                "City",
                "Stadt"
        };
        String[] searchButtonTexts = {
                "Buscar",
                "Search",
                "Suche"
        };
        String[] saveButtonTexts = {
                "Añadir",
                "Add",
                "Hinzufügen"
        };

        // Establecer los textos según el idioma seleccionado
        int languageIndex = getIntent().getIntExtra("languageIndex", 0);
        ((TextView) findViewById(R.id.title)).setText(movieTitleTexts[languageIndex]);
        ((TextView) findViewById(R.id.date_label)).setText(dateLabelTexts[languageIndex]);
        ((TextView) findViewById(R.id.city_label)).setText(cityLabelTexts[languageIndex]);
        ((Button) findViewById(R.id.search)).setText(searchButtonTexts[languageIndex]);
        ((Button) findViewById(R.id.save)).setText(saveButtonTexts[languageIndex]);

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("AgregarPeliculasActivity", "onClick");
                titulo = findViewById(R.id.filmtitle);

                selectedLanguage = getIntent().getStringExtra("selectedLanguage");

                String tituloParaUrl = titulo.getText().toString().replace(" ", "+");
                Log.d("url", "url: "+ url + tituloParaUrl);
                // Ejecutar la llamada a la API utilizando PeliculasApi
                PeliculasApi peliculasApi = new PeliculasApi(AgregarPeliculasActivity.this);
                peliculasApi.execute(url + tituloParaUrl);

            }
        });
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pelicula.setCiudad(cityEditText.getText().toString());
                pelicula.setFecha(dateEditText.getText().toString());

                if (pelicula.validarCampos()){
                    //Extraemos de la caché
                    List<Pelicula> peliculas = CacheService.getFilmsFromCache(getApplicationContext());

                    //Añadimos y guardamos en la caché
                    peliculas.add(pelicula);
                    CacheService.saveFilmsToCache(getApplicationContext(), peliculas);

                    logoImageView.setVisibility(View.INVISIBLE);
                    dateLabel.setVisibility(View.INVISIBLE);
                    dateEditText.setVisibility(View.INVISIBLE);
                    cityLabel.setVisibility(View.INVISIBLE);
                    cityEditText.setVisibility(View.INVISIBLE);
                    guardar.setVisibility(View.INVISIBLE);

                    finish();
                }

            }
        });
    }

    @Override
    public void onPeliculaLoaded(Pelicula pelicula) {
        // Asignar la película recibida a la variable local o de clase
        this.pelicula = pelicula;
        Log.d("Pelicula", "peli: "+ pelicula);

        // Mostrar los campos ahora que se han cargado los datos de la película
        Picasso.get().load(pelicula.getImagen()).into(logoImageView);

        logoImageView.setVisibility(View.VISIBLE);
        dateLabel.setVisibility(View.VISIBLE);
        dateEditText.setVisibility(View.VISIBLE);
        cityLabel.setVisibility(View.VISIBLE);
        cityEditText.setVisibility(View.VISIBLE);
        guardar.setVisibility(View.VISIBLE);


    }

}
