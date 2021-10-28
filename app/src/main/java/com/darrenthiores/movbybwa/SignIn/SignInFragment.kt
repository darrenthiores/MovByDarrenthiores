package com.darrenthiores.movbybwa.SignIn

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import com.darrenthiores.movbybwa.Navigation.MainNavActivity
import com.darrenthiores.movbybwa.R
import com.darrenthiores.movbybwa.SignUp.SignUpFragment
import com.darrenthiores.movbybwa.databinding.FragmentSignInBinding
import org.koin.android.ext.android.inject

class SignInFragment : Fragment() {

    private var _binding:FragmentSignInBinding? = null
    private val binding get() = _binding
    private val viewModel:SignInViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSignInBinding.inflate(layoutInflater, container, false)

        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            btSignIn.setOnClickListener {

                val username = edUsername.text.toString()
                val password = edPassword.text.toString()

                when {
                    username.isEmpty() -> edUsername.error = "This Field Cannot Be Empty!"
                    password.isEmpty() -> edPassword.error = "This Field Cannot Be Empty!"
                    else -> {
                        viewModel.checkUser(username).addOnCompleteListener {
                            if(it.isSuccessful){
                                val isExist = it.result?.exists() ?: false
                                if (isExist) {
                                    if (viewModel.checkPassword(it.result, password)) {
                                        onClickSignIn(username)
                                    } else {
                                        edPassword.error = "Wrong Password!!"
                                    }
                                } else {
                                    edUsername.error = "This Username Don't Exist!"
                                }
                            }
                        }
                    }
                }
            }

            btSignUp.setOnClickListener { onClickSignUp() }
        }
    }

    private fun onClickSignIn(username:String){
        viewModel.setUser(username)
        viewModel.setLogin(true)
        startActivity(Intent(activity, MainNavActivity::class.java))
    }

    private fun onClickSignUp(){
        parentFragmentManager.commit {
            replace(R.id.main_container, SignUpFragment(), SignUpFragment::class.java.simpleName).addToBackStack(SignInFragment::class.java.simpleName)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}