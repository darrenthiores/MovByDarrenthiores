package com.darrenthiores.movbybwa.myWallet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.darrenthiores.core.model.data.Transaction
import com.darrenthiores.core.model.data.User
import com.darrenthiores.core.ui.TransactionAdapter
import com.darrenthiores.movbybwa.R
import com.darrenthiores.movbybwa.databinding.FragmentMyWalletBinding
import org.koin.android.ext.android.inject

class MyWalletFragment : Fragment() {

    private var _binding:FragmentMyWalletBinding? = null
    private val binding get() = _binding
    private val viewModel:MyWalletViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMyWalletBinding.inflate(layoutInflater, container, false)

        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val transactionAdapter = TransactionAdapter()
        val transactionList = arrayListOf<Transaction>()
        transactionList.add(Transaction("Top Up", "27/10/2021", 280000.0))
        transactionList.add(Transaction("Buy (4) Ticket", "27/10/2021", -280000.0))

        transactionAdapter.setData(transactionList)

        binding?.apply {
            viewModel.getBalance().addOnCompleteListener {
                if(it.isSuccessful){
                    val data = it.result?.toObject(User::class.java)

                    tvMyWalletBalance.text = getString(R.string.text_balance, data?.balance.toString())

                    btTopUp.setOnClickListener {
                        val mBundle = Bundle()
                        mBundle.putDouble("BALANCE_KEY", data?.balance!!)
                        findNavController().navigate(R.id.action_myWalletFragment_to_topUpFragment, mBundle)
                    }

                }
            }

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