package com.darrenthiores.movbybwa.ticketDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.darrenthiores.core.model.data.CheckOut
import com.darrenthiores.core.ui.DetailSeatAdapter
import com.darrenthiores.movbybwa.databinding.FragmentTicketDetailBinding

class TicketDetailFragment : Fragment() {

    private var _binding:FragmentTicketDetailBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTicketDetailBinding.inflate(layoutInflater, container, false)

        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movie = TicketDetailFragmentArgs.fromBundle(arguments as Bundle).movie
        val ticketAdapter = DetailSeatAdapter()
        val checkOutList = arrayListOf<CheckOut>()
        checkOutList.add(CheckOut("B3", 70000))
        checkOutList.add(CheckOut("B4", 70000))

        ticketAdapter.setData(checkOutList)

        binding?.apply {
            ivDetailTicket.clipToOutline = true
            tvTicketDetailName.text = movie?.title
            tvTicketDetailGenre.text = movie?.genre
            tvTicketDetailRate.text = movie?.rating.toString()
            tvTicketDetailCinema.text = "Kelapa Gading XXI"
            tvTicketDetailDate.text = "26/10/2021"
            Glide.with(this@TicketDetailFragment)
                .load(movie?.poster)
                .into(ivDetailTicket)
            rvTicketDetail.layoutManager = LinearLayoutManager(activity)
            rvTicketDetail.adapter = ticketAdapter
            btUp.setOnClickListener { requireActivity().onBackPressed() }
            ivBarcode.setOnClickListener { BarcodeFragment().show(parentFragmentManager, BarcodeFragment::class.java.simpleName) }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}