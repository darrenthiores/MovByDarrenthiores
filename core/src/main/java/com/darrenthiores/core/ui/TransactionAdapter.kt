package com.darrenthiores.core.ui

import android.graphics.Color
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
import com.darrenthiores.core.model.data.Transaction

class TransactionAdapter : RecyclerView.Adapter< TransactionAdapter.TransactionViewHolder>() {

    private val transactionList = ArrayList<Transaction>()

    fun setData(data:ArrayList<Transaction>){
        transactionList.clear()
        transactionList.addAll(data)
        notifyDataSetChanged()
    }

    inner class TransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = TransactionItemBinding.bind(itemView)

        fun bind(transaction: Transaction ){
            binding.apply {
                tvTransactionName.text = transaction.title
                tvTransactionDate.text = transaction.date
                tvTransactionCost.text = itemView.resources.getString(R.string.text_balance, transaction.value.toString())
                if(transaction.value<0.0){
                    tvTransactionCost.setTextColor(Color.RED)
                } else {
                    tvTransactionCost.setTextColor(Color.GREEN)
                }
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TransactionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.transaction_item, parent, false)

        return TransactionViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.bind(transactionList[position])
    }

    override fun getItemCount(): Int = transactionList.size

}