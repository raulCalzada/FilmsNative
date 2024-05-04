package com.example.films_native;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class PeliculaAdapter extends BaseAdapter {
    private List<Pelicula> peliculas;
    private Context context;

    private int languageIndex;

    public PeliculaAdapter(Context context, List<Pelicula> peliculas, int languageIndex) {
        this.context = context;
        this.peliculas = peliculas;
        this.languageIndex = languageIndex;
    }

    @Override
    public int getCount() {
        return peliculas.size();
    }

    @Override
    public Object getItem(int position) {
        return peliculas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(context).inflate(R.layout.item_pelicula, parent, false);
        }

        Pelicula currentPelicula = peliculas.get(position);

        // Referenciar los elementos de la vista list_item_movie.xml
        ImageView movieImageView = listItemView.findViewById(R.id.movie_image);
        TextView titleTextView = listItemView.findViewById(R.id.movie_title);
        TextView actorTextView = listItemView.findViewById(R.id.movie_actor);
        TextView dateTextView = listItemView.findViewById(R.id.movie_date);
        TextView cityTextView = listItemView.findViewById(R.id.movie_city);
        TextView directorTextView = listItemView.findViewById(R.id.movie_director);

        // Asignar los valores de la película a los elementos de la vista
        Picasso.get().load(currentPelicula.getImagen()).into(movieImageView);
        titleTextView.setText(currentPelicula.getTitulo());
        actorTextView.setText(currentPelicula.getActor());
        dateTextView.setText(currentPelicula.getFecha());
        cityTextView.setText(currentPelicula.getCiudad());
        directorTextView.setText(currentPelicula.getDirector());

        Button deleteButton = listItemView.findViewById(R.id.delete_button);
        String[] deleteButtonLabels = {
                "Eliminar",
                "Delete",
                "Löschen"
        };
        deleteButton.setText(deleteButtonLabels[languageIndex]);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Eliminar la película seleccionada del listado
                peliculas.remove(position);

                CacheService.saveFilmsToCache(context, peliculas);

                notifyDataSetChanged();
            }
        });
        return listItemView;
    }
}

