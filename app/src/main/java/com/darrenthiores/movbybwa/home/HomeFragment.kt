package com.darrenthiores.movbybwa.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.darrenthiores.core.model.data.Movie
import com.darrenthiores.core.model.data.User
import com.darrenthiores.core.ui.ComingSoonAdapter
import com.darrenthiores.core.ui.NowPlayingAdapter
import com.darrenthiores.movbybwa.R
import com.darrenthiores.movbybwa.databinding.FragmentHomeBinding
import org.koin.android.ext.android.inject

class HomeFragment : Fragment() {

    private var _binding:FragmentHomeBinding? = null
    private val binding get() = _binding
    private val viewModel: HomeViewModel by inject()
    private val nowPlayingAdapter by lazy {
        NowPlayingAdapter(::onClickNowPlaying)
    }
    private val comingSoonAdapter by lazy {
        ComingSoonAdapter(::onClickComingSoon)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            viewModel.getData().addOnCompleteListener {
                if(it.isSuccessful){
                    val user = it.result?.toObject(User::class.java)
                    tvHomeName.text = user?.name
                    tvHomeBalance.text = getString(R.string.text_balance, user?.balance.toString())
                    if(user?.url?.isNotEmpty() == true){
                        Glide.with(this@HomeFragment)
                            .load(user.url)
                            .into(cvHome)
                    }
                }
            }
        }

        setUpRvComing()
        setUpRvPlaying()

    }

    private fun setUpRvPlaying(){
        viewModel.getMovies().addOnCompleteListener {
            if(it.isSuccessful){
                val movieArray = arrayListOf<Movie>()
                for(doc in it.result!!){
                    movieArray.add(doc.toObject(Movie::class.java))
                }
                nowPlayingAdapter.setData(movieArray)
                nowPlayingAdapter.notifyDataSetChanged()
            }
        }
        binding?.apply {
            rvNowPlaying.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            rvNowPlaying.adapter = nowPlayingAdapter
        }
    }

    private fun setUpRvComing(){
        viewModel.getComingSoon().addOnCompleteListener {
            if(it.isSuccessful){
                val comingSoonArray = arrayListOf<Movie>()
                for(doc in it.result!!){
                    comingSoonArray.add(doc.toObject(Movie::class.java))
                }
                comingSoonAdapter.setData(comingSoonArray)
                comingSoonAdapter.notifyDataSetChanged()
            }
        }
        binding?.apply {
            rvComingSoon.layoutManager = LinearLayoutManager(activity)
            rvComingSoon.adapter = comingSoonAdapter
        }
    }

    private fun onClickNowPlaying(movie: Movie){
        val toDetailFragment = HomeFragmentDirections.actionNavigationHomeToDetailFragment()
        toDetailFragment.movie = movie
        toDetailFragment.type = ON_PLAYING

        findNavController().navigate(toDetailFragment)

    }

    private fun onClickComingSoon(movie: Movie){
        val toDetailFragment = HomeFragmentDirections.actionNavigationHomeToDetailFragment()
        toDetailFragment.movie = movie
        toDetailFragment.type = COMING_SOON

        findNavController().navigate(toDetailFragment)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object{
        const val ON_PLAYING = "ON_PLAYING"
        const val COMING_SOON = "COMING_SOON"
    }

}