package com.sungil.wear.presentation.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.sungil.wear.databinding.ActivityMainBinding
import com.sungil.wear.presentation.main.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        test()
    }

    private fun test() {
        viewModel.liveData.observe(this, Observer {
            when (it) {
                is MainViewModel.ViewStatus.ViewLading -> {
                    Timber.d("Loading.....")
                }

                is MainViewModel.ViewStatus.WearRunningData -> {
                    Toast.makeText(this, it.running.toString(), Toast.LENGTH_SHORT).show()
                }

                is MainViewModel.ViewStatus.Error -> {
                    Toast.makeText(this, it.errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}

