package com.example.films_native;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Pelicula {
    public String titulo;
    public String actor;
    public String fecha;
    public String ciudad;
    public String director;
    public String imagen;

    public Pelicula() {
    }

    // Setters
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    // Getters
    public String getTitulo() {
        return titulo;
    }

    public String getActor() {
        return actor;
    }

    public String getFecha() {
        return fecha;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getDirector() {
        return director;
    }

    public String getImagen() {
        return imagen;
    }

    // Método para obtener la respuesta JSON y asignar los valores a los campos de la película
    public static Pelicula getJsonResponse(String jsonResponse) {
        Pelicula pelicula = new Pelicula();

        try {
            JSONObject jsonObject = new JSONObject(jsonResponse);

            // Asignar los valores del JSON a los campos de la película
            pelicula.titulo = jsonObject.getString("Title");String actorsString = jsonObject.getString("Actors");
            String[] actorsArray = actorsString.split(", ");
            if (actorsArray.length > 0) {
                pelicula.actor = actorsArray[0];
            }
            pelicula.director = jsonObject.getString("Director");
            pelicula.imagen = jsonObject.getString("Poster");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return pelicula;
    }

    public boolean validarCampos() {
        return titulo != null && !titulo.isEmpty() &&
                actor != null && !actor.isEmpty() &&
                fecha != null && !fecha.isEmpty() &&
                ciudad != null && !ciudad.isEmpty() &&
                director != null && !director.isEmpty() &&
                imagen != null && !imagen.isEmpty();
    }
}

