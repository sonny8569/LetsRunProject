package com.sungil.runningproejct_mvvm.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sungil.runningproejct_mvvm.R
import com.sungil.runningproejct_mvvm.databinding.ActivityMainBinding
import com.sungil.runningproejct_mvvm.main.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private val viewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        addListener()
    }

    private fun initView(){
        val layoutManger : RecyclerView.LayoutManager = LinearLayoutManager(this)
        binding.recyclerviewContent.layoutManager = layoutManger

        binding.txtOtherSns.setTextColor(getColor(R.color.gray))
    }

    private fun addListener(){
        viewModel.wearLiveData.observe(this , Observer {
            val data = it ?: return@Observer
            Timber.d("The Data is Comming $data")
        })


        binding.txtMySns.setOnClickListener {
            binding.txtOtherSns.setTextColor(getColor(R.color.gray))
            binding.txtMySns.setTextColor(getColor(R.color.white))

        }

        binding.txtOtherSns.setOnClickListener {
            binding.txtMySns.setTextColor(getColor(R.color.gray))
            binding.txtOtherSns.setTextColor(getColor(R.color.white))

        }
    }
}