package com.darrenthiores.movbybwa.ticket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.darrenthiores.core.model.data.Movie
import com.darrenthiores.core.ui.ComingSoonAdapter
import com.darrenthiores.movbybwa.R
import com.darrenthiores.movbybwa.databinding.FragmentTicketBinding
import org.koin.android.ext.android.inject

class TicketFragment : Fragment() {

    private var _binding:FragmentTicketBinding? = null
    private val binding get() = _binding
    private val viewModel: TicketViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTicketBinding.inflate(layoutInflater, container, false)

        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val ticketAdapter = ComingSoonAdapter(::onClickItem)
        val movieList = arrayListOf<Movie>()

        viewModel.getMovies().addOnCompleteListener {
            if(it.isSuccessful){
                for(data in it.result!!){
                    val movie = data.toObject(Movie::class.java)
                    movieList.add(movie)
                }
                binding?.tvTicketTotal?.text = getString(R.string.movie_today, movieList.size)
                ticketAdapter.setData(movieList)
            }
        }

        binding?.apply {
            rvTicket.layoutManager = LinearLayoutManager(activity)
            rvTicket.adapter = ticketAdapter

            btHistory.setOnClickListener {
                findNavController().navigate(R.id.action_navigation_ticket_to_historyFragment)
            }

        }

    }

    private fun onClickItem(movie:Movie){
        val toTicketDetail = TicketFragmentDirections.actionNavigationTicketToTicketDetailFragment()
        toTicketDetail.movie = movie
        findNavController().navigate(toTicketDetail)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}