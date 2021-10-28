package com.darrenthiores.movbybwa.checkOut

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.darrenthiores.core.model.data.CheckOut
import com.darrenthiores.core.model.data.User
import com.darrenthiores.core.ui.CheckOutAdapter
import com.darrenthiores.movbybwa.R
import com.darrenthiores.movbybwa.databinding.FragmentCheckOutBinding
import org.koin.android.ext.android.inject

class CheckOutFragment : Fragment() {

    private var _binding:FragmentCheckOutBinding? = null
    private val binding get() = _binding
    private val viewModel:CheckOutViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCheckOutBinding.inflate(layoutInflater, container, false)

        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val checkOutData = arguments?.getParcelableArrayList<CheckOut>(PickSeatFragment.SEAT_DATA)
        val checkOutAdapter = CheckOutAdapter()
        checkOutAdapter.setData(checkOutData!!)

        var total = 0

        for(data in checkOutData){
            total += data.price
        }

        binding?.apply {

            tvCheckOutCinema.text = "Kelapa Gading XXI"
            tvCheckOutDate.text = "26/10/2021"

            viewModel.getBalance().addOnCompleteListener {
                if(it.isSuccessful){
                    val data = it.result?.toObject(User::class.java)
                    updateUI(data?.balance, total)
                }
            }

            rvCheckOut.layoutManager = LinearLayoutManager(activity)
            rvCheckOut.adapter = checkOutAdapter

        }

        buttonFunctionality()

    }

    private fun updateUI(balance:Double?, total:Int){
        binding?.apply {

            tvCheckOutTotal.text = getString(R.string.text_balance, total.toString())
            tvCheckOutBalance.text = getString(R.string.text_balance, balance.toString())

            if(balance!!<total){
                tvCheckOutBalance.setTextColor(Color.RED)
                btPay.visibility = View.GONE
                tvCheckOutBalanceLess.visibility = View.VISIBLE
            } else {
                tvCheckOutBalance.setTextColor(Color.GREEN)
                btPay.visibility = View.VISIBLE
                tvCheckOutBalanceLess.visibility = View.GONE
            }

            btPay.setOnClickListener {
                val balanceAfterPay = balance - total
                viewModel.updateBalance(balanceAfterPay)
                findNavController().navigate(R.id.action_checkOutFragment_to_bookSuccessFragment)
            }

        }
    }

    private fun buttonFunctionality(){
        binding?.apply {
            btUp.setOnClickListener { requireActivity().onBackPressed() }
            btCancel.setOnClickListener { requireActivity().onBackPressed() }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}