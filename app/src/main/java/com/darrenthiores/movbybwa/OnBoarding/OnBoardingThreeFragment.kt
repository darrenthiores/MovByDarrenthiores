package com.darrenthiores.movbybwa.OnBoarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.darrenthiores.movbybwa.R
import com.darrenthiores.movbybwa.SignIn.SignInFragment
import com.darrenthiores.movbybwa.databinding.FragmentOnBoardingThreeBinding

class OnBoardingThreeFragment : Fragment() {

    private var _binding:FragmentOnBoardingThreeBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentOnBoardingThreeBinding.inflate(layoutInflater, container, false)

        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            btNext.setOnClickListener { onClickNext() }
        }

    }

    private fun onClickNext(){
        parentFragmentManager.commit {
            replace(R.id.main_container, SignInFragment(), SignInFragment::class.java.simpleName).addToBackStack(OnBoardingThreeFragment::class.java.simpleName)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}