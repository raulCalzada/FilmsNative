package com.example.films_native;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.BaseAdapter;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import java.util.List;


public class VerPeliculasActivity extends AppCompatActivity {

    private List<Pelicula> peliculas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listado_peliculas);

        int languageIndex = getIntent().getIntExtra("languageIndex", 0);

        Button backButton = findViewById(R.id.back);
        String[] backButtonLabels = {
                "Volver",
                "Back",
                "Zurück"
        };
        backButton.setText(backButtonLabels[languageIndex]);

        peliculas = CacheService.getFilmsFromCache(getApplicationContext());

        ListView listView = findViewById(R.id.list_view);

        listView.setAdapter(new PeliculaAdapter(this, peliculas,languageIndex));

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}

