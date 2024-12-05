package com.example.project

import org.json.JSONArray

data class Movie (
    val movieId: Int,
    val title: String,
    val overview: String,
    private val posterPath: String, // Corrected typo here
){
    val psoterImageUrl ="https://image.tmdb.org/t/p/w342/$posterPath"
    companion object{
        fun fromJsonArray(movieJSONArray: JSONArray): List<Movie> {
            val movies = mutableListOf<Movie>()
            for (i in 0 until movieJSONArray.length()){
                val movieJson = movieJSONArray.getJSONObject(i) // Corrected variable name here
                movies.add(
                    Movie(
                        movieJson.getInt("id"),
                        movieJson.getString("title"),
                        movieJson.getString("overview"),
                        movieJson.getString("poster_path") // Corrected order here
                    )
                )
            }
            return movies
        }
    }
}
