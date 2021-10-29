package com.darrenthiores.movbybwa.topUp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.darrenthiores.movbybwa.R
import com.darrenthiores.movbybwa.databinding.FragmentTopUpSuccessBinding

class TopUpSuccessFragment : Fragment() {

    private var _binding:FragmentTopUpSuccessBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTopUpSuccessBinding.inflate(layoutInflater, container, false)

        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {

            btHome.setOnClickListener {
                findNavController().navigate(R.id.action_topUpSuccessFragment_to_navigation_home)
            }

            btWallet.setOnClickListener {
                findNavController().navigate(R.id.action_topUpSuccessFragment_to_myWalletFragment)
            }

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}