package com.svalero.retrofitthemoviesdb;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.svalero.retrofitthemoviesdb.json_mapper.Movie;
import com.svalero.retrofitthemoviesdb.json_mapper.MovieResponse;
import com.svalero.retrofitthemoviesdb.retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private final String API_KEY = "TU_API_KEY";
    private final String LANGUAGE = "es-ES";
    private final int PAGE = 1;

    private void getPopularMovies() {
        Call<MovieResponse> call = RetrofitClient.getInstance().getPopularMovies(API_KEY, LANGUAGE, PAGE);

        // Ejecutamos la solicitud a la API de películas populares
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Movie> movies = response.body().getResults();

                    // Procesamos y mostramos las películas obtenidas
                    for (Movie myMovie : movies) {
                        Toast.makeText(MainActivity.this,
                                "Película: " + myMovie.getTitle(),
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // En caso de que la respuesta no sea exitosa
                    Toast.makeText(MainActivity.this,
                            "Error en la respuesta del servidor",
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                // Aquí mostramos un mensaje de error si la llamada falla
                Toast.makeText(MainActivity.this,
                        "Fallo en la solicitud: " + t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button popularMoviesButton = findViewById(R.id.button_popular);
        Button searchMoviesButton = findViewById(R.id.button_search);
        Button movieDetailsButton = findViewById(R.id.button_details);

        popularMoviesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPopularMovies(); // Llama a la función para obtener las películas populares
            }
        });

        searchMoviesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchMovies("Titanic"); // Ejemplo de búsqueda de películas
            }
        });

        movieDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMovieDetails(550); // Ejemplo de obtener detalles de una película por ID
            }
        });
    }

    private void searchMovies(String query) {
        Call<MovieResponse> call = RetrofitClient.getInstance()
                .searchMovies(API_KEY, LANGUAGE, query, 1);

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Movie> movies = response.body().getResults();
                    for (Movie movie : movies) {
                        Toast.makeText(MainActivity.this, "Buscar: " + movie.getTitle(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Error en la respuesta", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getMovieDetails(int movieId) {
        // Implementación de obtener detalles de película aquí
    }
}
