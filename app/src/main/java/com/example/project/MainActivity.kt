package com.example.project

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val movies= mutableListOf<com.example.flixster.Movie>()
    private lateinit var rvMovies: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(com.example.flixster.R.layout.activity_main)
        rvMovies = findViewById(com.example.flixster.R.id.rvMovies)

        val movieAdapter = com.example.flixster.MovieAdapter(this, movies)
        rvMovies.adapter = movieAdapter
        rvMovies.layoutManager = LinearLayoutManager(this)

        val client = com.codepath.asynchttpclient.AsyncHttpClient()
        client.get(com.example.flixster.NOW_PLAYING_URL, object: com.codepath.asynchttpclient.callback.JsonHttpResponseHandler(){
            override fun onFailure(
                statusCode: Int, headers: okhttp3.Headers?, response: String?, throwable: Throwable?
            ) {
                Log.e(com.example.flixster.TAG, "onFailure $statusCode")
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onSuccess(statusCode: Int, headers: okhttp3.Headers?, json: com.codepath.asynchttpclient.callback.JsonHttpResponseHandler.JSON) {
                Log.i(com.example.flixster.TAG, "onSuccess: JSON data $json")
                val movieJsonArray = json.jsonObject.getJSONArray("results")
                movies.addAll(com.example.flixster.Movie.Companion.fromJsonArray(movieJsonArray))
                movieAdapter.notifyDataSetChanged()
                Log.i(com.example.flixster.TAG, "Movie List $movies")

            }

        })


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(com.example.flixster.R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}