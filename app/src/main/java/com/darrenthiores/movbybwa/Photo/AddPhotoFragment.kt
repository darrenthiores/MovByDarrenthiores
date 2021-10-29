package com.darrenthiores.movbybwa.Photo

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
import com.darrenthiores.movbybwa.Navigation.MainNavActivity
import com.darrenthiores.movbybwa.R
import com.darrenthiores.movbybwa.databinding.FragmentAddPhotoBinding
import com.github.dhaval2404.imagepicker.ImagePicker
import org.koin.android.ext.android.inject

class AddPhotoFragment : Fragment() {

    private var _binding:FragmentAddPhotoBinding? = null
    private val binding get() = _binding
    private val viewModel:AddPhotoViewModel by inject()

    private var isAdd :Boolean = false
    private lateinit var filePath: Uri

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddPhotoBinding.inflate(layoutInflater, container, false)

        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {

            viewModel.getName().addOnCompleteListener {
                if(it.isSuccessful){
                    val name = it.result?.toObject(User::class.java)?.name
                    tvName.text = name.toString()
                }
            }

            btSave.setOnClickListener { onSaveCLicked(filePath) }
            btLater.setOnClickListener { onLaterClicked() }

            fabAddImg.setOnClickListener {
                if(isAdd){
                    isAdd = false
                    btSave.visibility = View.GONE
                    fabAddImg.setImageResource(R.drawable.ic_baseline_add_24)
                    cvProfile.setImageResource(R.drawable.ic_baseline_account_circle_24)
                } else {
                    ImagePicker.with(this@AddPhotoFragment)
                        .cameraOnly()
                        .start(REQUEST_FOR_PICTURE)
                }
            }

        }

    }

    private fun onSaveCLicked(filePath:Uri){

        if(filePath!=null){
            val dialog = ProgressDialog(activity)
            dialog.setTitle("Uploading...")
            dialog.show()

            val ref = viewModel.ref

            ref.putFile(filePath)
                .addOnSuccessListener {
                dialog.dismiss()

                ref.downloadUrl.addOnCompleteListener {
                    viewModel.updateData(it.result.toString()).addOnCompleteListener {
                        startActivity(Intent(activity, MainNavActivity::class.java))
                    }
                }
            }
                .addOnFailureListener {
                    dialog.dismiss()
                }

        }
    }

    private fun onLaterClicked(){
        startActivity(Intent(activity, MainNavActivity::class.java))
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode== REQUEST_FOR_PICTURE && resultCode == Activity.RESULT_OK){
            filePath = data?.data!!
            isAdd = true
            binding?.apply {
                btSave.visibility = View.VISIBLE
                fabAddImg.setImageResource(R.drawable.ic_baseline_delete_24)
                Glide.with(this@AddPhotoFragment)
                    .load(filePath)
                    .into(cvProfile)
            }
        }
    }

    companion object{
        private const val REQUEST_FOR_PICTURE = 30
    }

}