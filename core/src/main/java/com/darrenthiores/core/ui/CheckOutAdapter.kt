package com.darrenthiores.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.darrenthiores.core.R
import com.darrenthiores.core.databinding.ComingSoonItemBinding
import com.darrenthiores.core.databinding.PlayedItemBinding
import com.darrenthiores.core.databinding.SeatItemBinding
import com.darrenthiores.core.databinding.TransactionItemBinding
import com.darrenthiores.core.model.data.Cast
import com.darrenthiores.core.model.data.CheckOut
import com.darrenthiores.core.model.data.Movie

class CheckOutAdapter : RecyclerView.Adapter< CheckOutAdapter.CheckOutViewHolder>() {

    private val checkOutList = ArrayList<CheckOut>()

    fun setData(data:ArrayList<CheckOut>){
        checkOutList.clear()
        checkOutList.addAll(data)
        notifyDataSetChanged()
    }

    inner class CheckOutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = SeatItemBinding.bind(itemView)

        fun bind(checkOut: CheckOut ){
            binding.apply {
                tvSeatNumber.text = checkOut.seat
                tvSeatPrice.text = itemView.resources.getString(R.string.idr_item, checkOut.price)
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CheckOutViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.seat_item, parent, false)

        return CheckOutViewHolder(view)
    }

    override fun onBindViewHolder(holder: CheckOutViewHolder, position: Int) {
        holder.bind(checkOutList[position])
    }

    override fun getItemCount(): Int = checkOutList.size

}