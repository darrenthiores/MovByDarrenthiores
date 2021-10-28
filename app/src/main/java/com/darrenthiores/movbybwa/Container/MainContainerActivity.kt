package com.darrenthiores.movbybwa.Container

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.darrenthiores.movbybwa.OnBoarding.OnBoardingOneFragment
import com.darrenthiores.movbybwa.R
import com.google.firebase.FirebaseApp

class MainContainerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_container)

        supportFragmentManager.commit {
            add(R.id.main_container, OnBoardingOneFragment(), OnBoardingOneFragment::class.java.simpleName)
        }

    }
}