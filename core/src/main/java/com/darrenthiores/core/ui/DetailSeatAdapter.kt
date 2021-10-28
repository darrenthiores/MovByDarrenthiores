package com.darrenthiores.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.darrenthiores.core.R
import com.darrenthiores.core.databinding.*
import com.darrenthiores.core.model.data.Cast
import com.darrenthiores.core.model.data.CheckOut
import com.darrenthiores.core.model.data.Movie

class DetailSeatAdapter : RecyclerView.Adapter< DetailSeatAdapter.CheckOutViewHolder>() {

    private val checkOutList = ArrayList<CheckOut>()

    fun setData(data:ArrayList<CheckOut>){
        checkOutList.clear()
        checkOutList.addAll(data)
        notifyDataSetChanged()
    }

    inner class CheckOutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = DetailSeatItemBinding.bind(itemView)

        fun bind(checkOut: CheckOut ){
            binding.apply {
                tvSeatNumber.text = checkOut.seat
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CheckOutViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.detail_seat_item, parent, false)

        return CheckOutViewHolder(view)
    }

    override fun onBindViewHolder(holder: CheckOutViewHolder, position: Int) {
        holder.bind(checkOutList[position])
    }

    override fun getItemCount(): Int = checkOutList.size

}