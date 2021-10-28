package com.darrenthiores.movbybwa.myWallet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.darrenthiores.core.model.data.Transaction
import com.darrenthiores.core.ui.TransactionAdapter
import com.darrenthiores.movbybwa.R
import com.darrenthiores.movbybwa.databinding.FragmentMyWalletBinding

class MyWalletFragment : Fragment() {

    private var _binding:FragmentMyWalletBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMyWalletBinding.inflate(layoutInflater, container, false)

        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val myBalance = arguments?.getDouble("BALANCE_KEY", 0.0)
        val transactionAdapter = TransactionAdapter()
        val transactionList = arrayListOf<Transaction>()
        transactionList.add(Transaction("Top Up", "27/10/2021", 280000.0))
        transactionList.add(Transaction("Buy (4) Ticket", "27/10/2021", -280000.0))

        transactionAdapter.setData(transactionList)

        binding?.apply {
            tvMyWalletBalance.text = getString(R.string.text_balance, myBalance.toString())
            btUp.setOnClickListener { requireActivity().onBackPressed() }
            rvTransaction.layoutManager = LinearLayoutManager(activity)
            rvTransaction.adapter = transactionAdapter
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}