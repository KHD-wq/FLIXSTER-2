package com.example.project

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MovieAdapter(private val context: Context, private val movies: List<com.example.flixster.Movie>)
    : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.i(com.example.flixster.TAG, "onCreateViewHolder")
        val view = LayoutInflater.from(context)
            .inflate(com.example.flixster.R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount()=  movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.i(com.example.flixster.TAG, "onBindViewHolder position $position")
       val movie = movies[position]
        holder.bind(movie)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val Title = itemView.findViewById<TextView>(com.example.flixster.R.id.Title)
        private val Overview = itemView.findViewById<TextView>(com.example.flixster.R.id.Overview)
        private val ivPoster= itemView.findViewById<ImageView>(com.example.flixster.R.id.ivPoster)
        fun bind(movie: com.example.flixster.Movie){
            Title.text = movie.title
            Overview.text = movie.overview
            com.bumptech.glide.Glide.with(context).load(movie.psoterImageUrl).into(ivPoster)


        }
    }

}