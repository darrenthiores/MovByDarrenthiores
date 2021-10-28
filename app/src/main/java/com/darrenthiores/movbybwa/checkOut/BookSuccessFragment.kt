package com.darrenthiores.movbybwa.checkOut

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.darrenthiores.movbybwa.R
import com.darrenthiores.movbybwa.databinding.FragmentBookSuccessBinding

class BookSuccessFragment : Fragment() {

    private var _binding:FragmentBookSuccessBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentBookSuccessBinding.inflate(layoutInflater, container, false)

        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            btHome.setOnClickListener { onClickHome() }
            btTicket.setOnClickListener { onClickTicket() }
        }

    }

    private fun onClickTicket(){
        findNavController().navigate(R.id.action_bookSuccessFragment_to_navigation_ticket)
    }

    private fun onClickHome(){
        findNavController().navigate(R.id.action_bookSuccessFragment_to_navigation_home)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}