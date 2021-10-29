package com.darrenthiores.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.darrenthiores.core.R
import com.darrenthiores.core.databinding.ComingSoonItemBinding
import com.darrenthiores.core.databinding.NowPlayingItemBinding
import com.darrenthiores.core.model.data.Movie
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions

class ComingSoonAdapter(
    private val onClick: (Movie) -> Unit
) : RecyclerView.Adapter< ComingSoonAdapter.ComingViewHolder>() {

    private val movieList = ArrayList<Movie>()

    fun setData(data:ArrayList<Movie>){
        movieList.clear()
        movieList.addAll(data)
        notifyDataSetChanged()
    }

    inner class ComingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ComingSoonItemBinding.bind(itemView)

        fun bind(movie:Movie){
            binding.apply {
                ivComingSoon.clipToOutline = true
                tvComingSoonName.text = movie.title
                tvComingSoonGenre.text = movie.genre
                tvComingSoonRate.text = movie.rating.toString()
                Glide.with(itemView)
                    .load(movie.poster)
                    .into(ivComingSoon)
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ComingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.coming_soon_item, parent, false)

        return ComingViewHolder(view)
    }

    override fun onBindViewHolder(holder: ComingViewHolder, position: Int) {
        holder.bind(movieList[position])
        holder.itemView.setOnClickListener { onClick(movieList[position]) }
    }

    override fun getItemCount(): Int = movieList.size

}