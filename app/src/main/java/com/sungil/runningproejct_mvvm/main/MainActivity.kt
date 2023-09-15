package com.sungil.runningproejct_mvvm.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sungil.runningproejct_mvvm.R
import com.sungil.runningproejct_mvvm.activityRate.RateRunningDistanceActivity
import com.sungil.runningproejct_mvvm.databinding.ActivityMainBinding
import com.sungil.runningproejct_mvvm.main.bottomSheet.PostSnsBottomSheet
import com.sungil.runningproejct_mvvm.main.viewModel.MainViewModel
import com.sungil.runningproejct_mvvm.utill.Define
import com.sungil.runningproejct_mvvm.utill.SetOnClickListener
import com.sungil.runningproejct_mvvm.utill.PostAdapter
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    private val viewModel: MainViewModel by viewModels()
    private val postAdapter by lazy {
        PostAdapter()
    }

    private val requestPermissionsLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
        val allPermissionsGranted = permissions.all { it.value }
        if (allPermissionsGranted) {
            getRateActivity()
            return@registerForActivityResult
        }
        Toast.makeText(this , getString(R.string.msg_agree_permission) , Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        addListener()
    }

    private fun initView() {
        val layoutManger: RecyclerView.LayoutManager = LinearLayoutManager(this)
        binding.recyclerviewContent.layoutManager = layoutManger
        binding.txtOtherSns.setTextColor(getColor(R.color.gray))
    }

    private fun addListener() {

        binding.txtMySns.setOnClickListener {
            binding.txtOtherSns.setTextColor(getColor(R.color.gray))
            binding.txtMySns.setTextColor(getColor(R.color.white))
            viewModel.clickFollower()
        }

        binding.txtOtherSns.setOnClickListener {
            binding.txtMySns.setTextColor(getColor(R.color.gray))
            binding.txtOtherSns.setTextColor(getColor(R.color.white))
            viewModel.clickUnFollower()
        }

        viewModel.liveData.observe(this, Observer {
            when (it) {
                is MainViewModel.ViewStatus.ViewLoading -> {
                    Timber.d("Loading.....")
                }

                is MainViewModel.ViewStatus.FollowerLiveData -> {
                    Timber.d("Success to get Follower")
                }

                is MainViewModel.ViewStatus.PostDataLiveData -> {
                    Timber.d("The Post data  is Come")
                    val postData = it.data
                    postAdapter.data = postData
                }

                is MainViewModel.ViewStatus.SetNewFollowerLiveData -> {
                    Timber.d("Success to set New Follower")
                    Toast.makeText(this, "Success to follower $it", Toast.LENGTH_SHORT).show()
                }

                is MainViewModel.ViewStatus.SendPostLiveData -> {
                    Timber.d("Success to send New Post")
                    Toast.makeText(this, "Success to Post Data", Toast.LENGTH_SHORT).show()
                }


                is MainViewModel.ViewStatus.ViewError -> {
                    Timber.e("ERROR")
                    Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
                }

            }
        })

        postAdapter.setOnClickListener(object : SetOnClickListener {
            override fun onValueClick(data: String) {
                Timber.d("user Select Follower $data")
                viewModel.writeNewFollower(data)
            }
        })

        binding.btnPostSns.setOnClickListener {
            setPostSnsBottomSheet()
        }
        binding.icIcon.setOnClickListener {
            checkPermission()
        }
        binding.recyclerviewContent.adapter = postAdapter

    }

    private fun setPostSnsBottomSheet() {
        val bottomSheet: PostSnsBottomSheet = PostSnsBottomSheet {
            if (it.content == null) {
                return@PostSnsBottomSheet
            }
            viewModel.postSns(it, getCurrentTimeInMillis())
            Toast.makeText(this, getString(R.string.msg_post_sns), Toast.LENGTH_SHORT).show()
        }
        val bundle = Bundle()
        bundle.putString(Define.KM_KEY , viewModel.getRunningData())
        bottomSheet.show(supportFragmentManager, bottomSheet.tag)
    }

    private fun getCurrentTimeInMillis(): Long {
        return System.currentTimeMillis()
    }

    private fun checkPermission() {
        val permissions = arrayOf(
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        )
        requestPermissionsLauncher.launch(permissions)
    }
    private fun getRateActivity() {
        val intent = Intent(this, RateRunningDistanceActivity::class.java)
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
        viewModel.checkRunningData()
    }
}