package com.darrenthiores.movbybwa.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.darrenthiores.core.model.data.Movie
import com.darrenthiores.core.ui.ComingSoonAdapter
import com.darrenthiores.movbybwa.R
import com.darrenthiores.movbybwa.databinding.FragmentHistoryBinding
import org.koin.android.ext.android.inject

class HistoryFragment : Fragment() {

    private var _binding:FragmentHistoryBinding? = null
    private val binding get() = _binding
    private val viewModel: HistoryViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHistoryBinding.inflate(layoutInflater, container, false)

        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val historyAdapter = ComingSoonAdapter(::onClick)
        val movieList = arrayListOf<Movie>()

        viewModel.getMovies().addOnCompleteListener {
            if(it.isSuccessful){
                for(data in it.result!!){
                    val movie = data.toObject(Movie::class.java)
                    movieList.add(movie)
                }
                binding?.tvHistoryTotal?.text = getString(R.string.movie_today, movieList.size)
                historyAdapter.setData(movieList)
            }
        }

        binding?.apply {
            rvHistory.layoutManager = LinearLayoutManager(activity)
            rvHistory.adapter = historyAdapter

            btUp.setOnClickListener { requireActivity().onBackPressed() }
        }

    }

    private fun onClick(movie: Movie){

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}