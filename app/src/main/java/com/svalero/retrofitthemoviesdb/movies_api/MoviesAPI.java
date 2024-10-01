package com.svalero.retrofitthemoviesdb.movies_api;

import com.svalero.retrofitthemoviesdb.json_mapper.Movie;
import com.svalero.retrofitthemoviesdb.json_mapper.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MoviesAPI {
    // Obtener películas populares
    @GET("movie/popular?api_key=61e0d26ead78a0b630a6ffe401e15a6a")
    Call<MovieResponse> getPopularMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
    );

    // Buscar películas
    @GET("search/movie?api_key=61e0d26ead78a0b630a6ffe401e15a6a")
    Call<MovieResponse> searchMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("query") String query,
            @Query("page") int page
    );

    // Obtener detalles de una película por ID
    @GET("movie/{movie_id}?api_key=61e0d26ead78a0b630a6ffe401e15a6a")
    Call<Movie> getMovieDetails(
            @Path("movie_id") int movieId,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );
}
