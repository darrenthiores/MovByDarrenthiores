package com.darrenthiores.movbybwa.SignUp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import com.darrenthiores.core.model.data.User
import com.darrenthiores.movbybwa.Photo.AddPhotoFragment
import com.darrenthiores.movbybwa.R
import com.darrenthiores.movbybwa.databinding.FragmentSignUpBinding
import org.koin.android.ext.android.inject

class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding
    private val viewModel:SignUpViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSignUpBinding.inflate(layoutInflater, container, false)

        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            btUp.setOnClickListener { onBackPressed() }
            btSignUp.setOnClickListener {
                val name = edName.text.toString()
                val email = edEmail.text.toString()
                val username = edUsername.text.toString()
                val password = edPassword.text.toString()

                when{
                    name.isEmpty() -> edName.error = errorMessage
                    email.isEmpty() -> edEmail.error = errorMessage
                    !isValidEmail(email) -> edEmail.error = emailError
                    username.isEmpty() -> edUsername.error = errorMessage
                    username.length <= 4 -> edUsername.error = "Username length must be at least 5!"
                    password.isEmpty() -> edPassword.error = errorMessage
                    password.length <= 7 -> edPassword.error = "Password Length must be at least 8!"
                    else -> {
                        viewModel.checkUser(username).addOnCompleteListener { checking ->
                            if(checking.isSuccessful){
                                val isExist = checking.result?.exists() ?: false
                                if(isExist){
                                    edUsername.error = "Username Already Used!"
                                } else {
                                    val user = User(name=name, email = email, username = username, password = password)
                                    viewModel.setUser(user).addOnCompleteListener { set ->
                                        if (set.isSuccessful){
                                            onClickSignUp(username)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }

    }

    private fun onClickSignUp(username:String){
        viewModel.setUsername(username)
        viewModel.setLogin(true)
        parentFragmentManager.commit {
            replace(R.id.main_container, AddPhotoFragment(), AddPhotoFragment::class.java.simpleName)
        }
    }

    private fun onBackPressed(){
        parentFragmentManager.popBackStack()
    }

    private fun isValidEmail(email: CharSequence): Boolean = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object{
        private const val errorMessage = "This Field Cannot Be Empty!"
        private const val emailError = "Email Invalid!"
    }

}