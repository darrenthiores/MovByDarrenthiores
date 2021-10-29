package com.darrenthiores.movbybwa.topUp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.darrenthiores.movbybwa.R
import com.darrenthiores.movbybwa.databinding.FragmentTopUpBinding
import org.koin.android.ext.android.inject

class TopUpFragment : Fragment() {

    private var _binding:FragmentTopUpBinding? = null
    private val binding get()=_binding
    private val viewModel:TopUpViewModel by inject()
    private var topUp = 0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTopUpBinding.inflate(layoutInflater, container, false)

        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val balance = arguments?.getDouble("BALANCE_KEY", 0.0)

        topUpClick()
        onClickTopUp(balance)

        binding?.apply {

            tvTopUpBalance.text = getString(R.string.text_balance, balance.toString())
            btUp.setOnClickListener { requireActivity().onBackPressed() }

        }

    }

    private fun topUpClick(){
        binding?.apply {

            edCustom.setOnClickListener{
                tv20k.setBackgroundResource(R.drawable.empty)
                tv50k.setBackgroundResource(R.drawable.empty)
                tv100k.setBackgroundResource(R.drawable.empty)
                tv200k.setBackgroundResource(R.drawable.empty)
                tv300k.setBackgroundResource(R.drawable.empty)
                tv500k.setBackgroundResource(R.drawable.empty)
                topUp = 0.0
            }

            tv20k.setOnClickListener {
                tv20k.setBackgroundResource(R.drawable.selected_top_up)
                tv50k.setBackgroundResource(R.drawable.empty)
                tv100k.setBackgroundResource(R.drawable.empty)
                tv200k.setBackgroundResource(R.drawable.empty)
                tv300k.setBackgroundResource(R.drawable.empty)
                tv500k.setBackgroundResource(R.drawable.empty)
                edCustom.setText("")
                topUp = 20000.0
            }

            tv50k.setOnClickListener {
                tv20k.setBackgroundResource(R.drawable.empty)
                tv50k.setBackgroundResource(R.drawable.selected_top_up)
                tv100k.setBackgroundResource(R.drawable.empty)
                tv200k.setBackgroundResource(R.drawable.empty)
                tv300k.setBackgroundResource(R.drawable.empty)
                tv500k.setBackgroundResource(R.drawable.empty)
                edCustom.setText("")
                topUp = 50000.0
            }

            tv100k.setOnClickListener {
                tv20k.setBackgroundResource(R.drawable.empty)
                tv50k.setBackgroundResource(R.drawable.empty)
                tv100k.setBackgroundResource(R.drawable.selected_top_up)
                tv200k.setBackgroundResource(R.drawable.empty)
                tv300k.setBackgroundResource(R.drawable.empty)
                tv500k.setBackgroundResource(R.drawable.empty)
                edCustom.setText("")
                topUp = 100000.0
            }

            tv200k.setOnClickListener {
                tv20k.setBackgroundResource(R.drawable.empty)
                tv50k.setBackgroundResource(R.drawable.empty)
                tv100k.setBackgroundResource(R.drawable.empty)
                tv200k.setBackgroundResource(R.drawable.selected_top_up)
                tv300k.setBackgroundResource(R.drawable.empty)
                tv500k.setBackgroundResource(R.drawable.empty)
                edCustom.setText("")
                topUp = 200000.0
            }

            tv300k.setOnClickListener {
                tv20k.setBackgroundResource(R.drawable.empty)
                tv50k.setBackgroundResource(R.drawable.empty)
                tv100k.setBackgroundResource(R.drawable.empty)
                tv200k.setBackgroundResource(R.drawable.empty)
                tv300k.setBackgroundResource(R.drawable.selected_top_up)
                tv500k.setBackgroundResource(R.drawable.empty)
                edCustom.setText("")
                topUp = 300000.0
            }

            tv500k.setOnClickListener {
                tv20k.setBackgroundResource(R.drawable.empty)
                tv50k.setBackgroundResource(R.drawable.empty)
                tv100k.setBackgroundResource(R.drawable.empty)
                tv200k.setBackgroundResource(R.drawable.empty)
                tv300k.setBackgroundResource(R.drawable.empty)
                tv500k.setBackgroundResource(R.drawable.selected_top_up)
                edCustom.setText("")
                topUp = 500000.0
            }

        }
    }

    private fun onClickTopUp(balance:Double?){

        binding?.apply {
            btTopUp.setOnClickListener {
                val customTopUp = edCustom.text.toString()
                when{
                    customTopUp.isNotEmpty() -> {
                        val topUp = customTopUp.toDouble()
                        if(topUp<=10000.0){
                            edCustom.error = "Top Up Cannot Be Less Than 10.000"
                        } else {
                            val updatedBalance = balance!! + topUp
                            updateBalance(updatedBalance)
                        }
                    }
                    topUp!=0.0 -> {
                        val updatedBalance = balance!! + topUp
                        updateBalance(updatedBalance)
                    }
                    else -> {
                        Toast.makeText(activity, "Top up 0?", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }

    private fun updateBalance(topUp:Double){
        viewModel.updateBalance(topUp).addOnCompleteListener {
            if(it.isSuccessful){
                findNavController().navigate(R.id.action_topUpFragment_to_topUpSuccessFragment)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}