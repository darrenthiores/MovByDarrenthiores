package com.darrenthiores.movbybwa.ticketDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.darrenthiores.movbybwa.databinding.FragmentBarcodeBinding

class BarcodeFragment : DialogFragment() {

    private var _binding:FragmentBarcodeBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentBarcodeBinding.inflate(layoutInflater, container, false)

        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            btClose.setOnClickListener { dialog?.cancel() }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}