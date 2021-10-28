package com.darrenthiores.movbybwa.profile

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.darrenthiores.core.model.data.User
import com.darrenthiores.movbybwa.R
import com.darrenthiores.movbybwa.databinding.FragmentProfileBinding
import org.koin.android.ext.android.inject

class ProfileFragment : Fragment() {

    private var _binding:FragmentProfileBinding? = null
    private val binding get() = _binding
    private val viewModel:ProfileViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProfileBinding.inflate(layoutInflater, container, false)

        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {

            viewModel.getProfile().addOnCompleteListener {
                if(it.isSuccessful){
                    val user = it.result?.toObject(User::class.java)
                    tvSettingName.text = user?.name
                    tvProfileEmail.text = user?.email
                    if(user?.url?.isNotEmpty()==true){
                        Glide.with(this@ProfileFragment)
                            .load(user.url)
                            .into(cvSetting)
                    }
                    btEditProfile.setOnClickListener {
                        val toEditProfile = ProfileFragmentDirections.actionNavigationProfileToEditProfileFragment()
                        toEditProfile.user = user
                        findNavController().navigate(toEditProfile)
                    }
                    btWallet.setOnClickListener {
                        val mBundle = Bundle()
                        mBundle.putDouble("BALANCE_KEY", user?.balance!!)
                        findNavController().navigate(R.id.action_navigation_profile_to_myWalletFragment, mBundle)
                    }
                }
            }

            btLanguage.setOnClickListener {
                val intent = Intent(Intent(Settings.ACTION_LOCALE_SETTINGS))

                startActivity(intent)
            }

            btHelp.setOnClickListener {

            }

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}