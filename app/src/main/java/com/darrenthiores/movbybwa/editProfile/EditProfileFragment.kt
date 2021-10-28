package com.darrenthiores.movbybwa.editProfile

import android.Manifest
import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.darrenthiores.core.model.data.User
import com.darrenthiores.movbybwa.Photo.AddPhotoFragment
import com.darrenthiores.movbybwa.databinding.FragmentEditProfileBinding
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import org.koin.android.ext.android.inject

class EditProfileFragment : Fragment(), PermissionListener {

    private var _binding: FragmentEditProfileBinding? = null
    private val binding get() = _binding
    private val viewModel:EditProfileViewModel by inject()
    private var filePath:Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentEditProfileBinding.inflate(layoutInflater, container, false)

        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = EditProfileFragmentArgs.fromBundle(arguments as Bundle).user

        binding?.apply {
            edName.setText(user?.name)
            edUsername.setText(user?.username)
            edEmail.setText(user?.email)
            edPassword.setText(user?.password)
            fabAddImg.setOnClickListener {
                Dexter.withActivity(activity)
                    .withPermission(Manifest.permission.CAMERA)
                    .withListener(this@EditProfileFragment)
                    .check()
            }
            btUp.setOnClickListener {
                requireActivity().onBackPressed()
            }
        }

        onClick()

    }

    private fun onClick(){

        binding?.apply {
            btSignUp.setOnClickListener {

                val name = edName.text.toString()
                val email = edEmail.text.toString()
                val password = edPassword.text.toString()

                when{
                    name.isEmpty() -> edName.error = errorMessage
                    email.isEmpty() -> edEmail.error = errorMessage
                    !isValidEmail(email) -> edEmail.error = emailError
                    password.isEmpty() -> edPassword.error = errorMessage
                    password.length <= 7 -> edPassword.error = "Password Length must be at least 8!"
                    else -> {
                        viewModel.updateData(name, email, password).addOnCompleteListener {
                            if(it.isSuccessful){
                                Toast.makeText(activity, "Data Updated..", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }

                if(filePath!=null){
                    val dialog = ProgressDialog(activity)
                    dialog.setTitle("Uploading...")
                    dialog.show()

                    val ref = viewModel.ref

                    ref.putFile(filePath!!)
                        .addOnSuccessListener {
                            dialog.dismiss()

                            ref.downloadUrl.addOnCompleteListener {
                                viewModel.updateImage(it.toString()).addOnCompleteListener { task ->
                                    if(task.isSuccessful){
                                        Toast.makeText(activity, "Image Updated!", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }
                        }
                        .addOnFailureListener {
                            dialog.dismiss()
                        }
                }

                Toast.makeText(activity, "Updating Data... Please Wait...", Toast.LENGTH_SHORT).show()

            }
        }

    }

    private fun isValidEmail(email: CharSequence): Boolean = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode== REQUEST_FOR_PICTURE && resultCode == Activity.RESULT_OK){
            val bitmap = data?.extras?.get("data") as Bitmap
            binding?.apply {
                Glide.with(this@EditProfileFragment)
                    .load(bitmap)
                    .into(cvProfile)
            }
            filePath = data.data!!
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object{
        private const val errorMessage = "This Field Cannot Be Empty!"
        private const val emailError = "Email Invalid!"
        private const val REQUEST_FOR_PICTURE = 30
    }

    override fun onPermissionGranted(response: PermissionGrantedResponse?) {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, REQUEST_FOR_PICTURE)
    }

    override fun onPermissionDenied(response: PermissionDeniedResponse?) {
        Toast.makeText(activity, "Permission Denied!", Toast.LENGTH_SHORT).show()
    }

    override fun onPermissionRationaleShouldBeShown(
        permission: PermissionRequest?,
        token: PermissionToken?
    ) {

    }

}