package com.darrenthiores.movbybwa.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.darrenthiores.core.model.data.Cast
import com.darrenthiores.core.model.data.Movie
import com.darrenthiores.core.ui.CastAdapter
import com.darrenthiores.movbybwa.databinding.FragmentDetailBinding
import com.darrenthiores.movbybwa.home.HomeFragment
import org.koin.android.ext.android.inject

class DetailFragment : Fragment() {

    private var _binding:FragmentDetailBinding? = null
    private val binding get() = _binding
    private val castAdapter = CastAdapter()
    private val viewModel: DetailViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDetailBinding.inflate(layoutInflater, container, false)

        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movie = DetailFragmentArgs.fromBundle(arguments as Bundle).movie
        val type = DetailFragmentArgs.fromBundle(arguments as Bundle).type

        binding?.apply {

            if(type== HomeFragment.COMING_SOON){
                btPickSeat.visibility = View.GONE
            } else {
                btPickSeat.visibility = View.VISIBLE
            }

            btPickSeat.setOnClickListener {
                onClickPickSeat(movie?.title)
            }

            btUp.setOnClickListener { requireActivity().onBackPressed() }

        }

        populateMovie(movie)
        setUpRvCast(type, movie?.id!!)

    }

    private fun populateMovie(movie: Movie?){
        binding?.apply {
            tvDetailName.text = movie?.title
            tvDetailGenre.text = movie?.genre
            tvDetailStory.text = movie?.description
            tvDetailRate.text = movie?.rating.toString()
            Glide.with(this@DetailFragment)
                .load(movie?.poster)
                .into(collapsingImg)
        }
    }

    private fun setUpRvCast(type:String, id:String){

        val castList = arrayListOf<Cast>()

        if(type== HomeFragment.COMING_SOON){
            viewModel.getComingSoonCast(id).addOnCompleteListener {
                if(it.isSuccessful){
                    for(cast in it.result!!){
                        castList.add(cast.toObject(Cast::class.java))
                    }
                    castAdapter.setData(castList)
                    castAdapter.notifyDataSetChanged()
                }
            }
        } else {
            viewModel.getMovieCast(id).addOnCompleteListener {
                if(it.isSuccessful){
                    for(cast in it.result!!){
                        castList.add(cast.toObject(Cast::class.java))
                    }
                    castAdapter.setData(castList)
                    castAdapter.notifyDataSetChanged()
                }
            }
        }

        binding?.apply {
            rvActor.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            rvActor.adapter = castAdapter
        }
    }

    private fun onClickPickSeat(title:String?){

        val toPickSeat = DetailFragmentDirections.actionDetailFragmentToPickSeatFragment()
        toPickSeat.title = title

        findNavController().navigate(toPickSeat)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}