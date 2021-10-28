package com.darrenthiores.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.darrenthiores.core.R
import com.darrenthiores.core.databinding.NowPlayingItemBinding
import com.darrenthiores.core.model.data.Movie
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions

class NowPlayingAdapter(
    private val onClick: (Movie) -> Unit
) : RecyclerView.Adapter< NowPlayingAdapter.NowPlayingViewHolder>() {

    private val movieList = ArrayList<Movie>()

    fun setData(data:ArrayList<Movie>){
        movieList.clear()
        movieList.addAll(data)
        notifyDataSetChanged()
    }

    inner class NowPlayingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = NowPlayingItemBinding.bind(itemView)

        fun bind(movie:Movie){
            binding.apply {
                tvNowPlayingName.text = movie.title
                tvNowPlayingGenre.text = movie.genre
                tvNowPlayingRate.text = movie.rating.toString()
                Glide.with(itemView)
                    .load(movie.poster)
                    .into(ivNowPlaying)
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NowPlayingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.now_playing_item, parent, false)

        return NowPlayingViewHolder(view)
    }

    override fun onBindViewHolder(holder: NowPlayingViewHolder, position: Int) {
        holder.bind(movieList[position])
        holder.itemView.setOnClickListener { onClick(movieList[position]) }
    }

    override fun getItemCount(): Int = movieList.size

}