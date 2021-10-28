package com.darrenthiores.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.darrenthiores.core.R
import com.darrenthiores.core.databinding.ComingSoonItemBinding
import com.darrenthiores.core.databinding.PlayedItemBinding
import com.darrenthiores.core.model.data.Cast
import com.darrenthiores.core.model.data.Movie

class CastAdapter : RecyclerView.Adapter< CastAdapter.CastViewHolder>() {

    private val castList = ArrayList<Cast>()

    fun setData(data:ArrayList<Cast>){
        castList.clear()
        castList.addAll(data)
        notifyDataSetChanged()
    }

    inner class CastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = PlayedItemBinding.bind(itemView)

        fun bind(cast: Cast){
            binding.apply {
                tvPlayedActor.text = cast.name
                Glide.with(itemView)
                    .load(cast.photo)
                    .into(circleImageView)
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CastViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.played_item, parent, false)

        return CastViewHolder(view)
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        holder.bind(castList[position])
    }

    override fun getItemCount(): Int = castList.size

}