package com.gekaradchenko.moviescheck.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.gekaradchenko.moviescheck.R
import com.gekaradchenko.moviescheck.databinding.ActivitySplashScreenBinding

import com.gekaradchenko.moviescheck.helper.NetworkConnection
import com.gekaradchenko.moviescheck.ui.main.MainActivity
import com.gekaradchenko.moviescheck.ui.splash.viewmodel.SplashScreenActivityViewModel
import com.gekaradchenko.moviescheck.ui.splash.viewmodel.SplashScreenActivityViewModelFactory

private lateinit var binding: ActivitySplashScreenBinding
private lateinit var viewModel: SplashScreenActivityViewModel

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen)
//        val factory = SplashScreenActivityViewModelFactory(applicationContext)
        viewModel = ViewModelProvider(this)[SplashScreenActivityViewModel::class.java]
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val networkConnection = NetworkConnection(applicationContext)
        networkConnection.observe(this, { isConnected ->
            if (isConnected) {

                Handler(Looper.getMainLooper()).postDelayed({
                    viewModel.onNavigate()
                }, 2_000)
            }

            binding.inet.text = "Inet = $isConnected"
        })



        viewModel.navigationEvent.observe(this, ::navigate)


    }

    private fun navigate(b: Boolean) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
        overridePendingTransition(R.anim.alpha_from_0, R.anim.alpha_from_1)

    }


}