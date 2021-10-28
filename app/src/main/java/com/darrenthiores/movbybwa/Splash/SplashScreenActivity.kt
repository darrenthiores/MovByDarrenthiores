package com.darrenthiores.movbybwa.Splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import com.darrenthiores.movbybwa.Container.MainContainerActivity
import com.darrenthiores.movbybwa.Navigation.MainNavActivity
import com.darrenthiores.movbybwa.R
import org.koin.android.ext.android.inject

class SplashScreenActivity : AppCompatActivity() {

    private val viewModel:SplashViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val delay: Long = 3000

        window.setFlags(

            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN

        )

        Handler(Looper.getMainLooper()).postDelayed({

            when{
                viewModel.isLogin -> startActivity(Intent(this, MainNavActivity::class.java))
                else -> startActivity(Intent(this, MainContainerActivity::class.java))
            }

            finish()

        }, delay)

    }
}