package com.sungil.runningproejct_mvvm.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sungil.runningproejct_mvvm.R
import com.sungil.runningproejct_mvvm.dataObject.FirebasePostData
import com.sungil.runningproejct_mvvm.databinding.ActivityMainBinding
import com.sungil.runningproejct_mvvm.main.bottomSheet.PostSnsBottomSheet
import com.sungil.runningproejct_mvvm.main.viewModel.MainViewModel
import com.sungil.runningproejct_mvvm.utill.SetOnClickListener
import com.sungil.runningproejct_mvvm.utill.PostAdapter
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }
    private val postAdapter = PostAdapter()

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
            viewModel.requestFollowerPost()
        }

        binding.txtOtherSns.setOnClickListener {
            binding.txtMySns.setTextColor(getColor(R.color.gray))
            binding.txtOtherSns.setTextColor(getColor(R.color.white))
            viewModel.requestUnFollowerPost()
        }


        viewModel.followerLiveData.observe(this, Observer { followerStatus ->
            when (followerStatus) {
                is MainViewModel.MainStatus.MainLoading -> {
                    Timber.d("Loading for get Follower")
                }

                is MainViewModel.MainStatus.MainSuccess -> {
                    Timber.d("Success to get Follower get Follower Post")
                }

                is MainViewModel.MainStatus.MainError -> {
                    Timber.e("ERROR to get Follower")
                    Toast.makeText(this, "The Follower is Empty", Toast.LENGTH_SHORT).show()
                }
            }

        })


        viewModel.postData.observe(this, Observer { postStatus ->
            when (postStatus) {
                is MainViewModel.MainStatus.MainLoading -> {
                    Timber.d("Loading for get Post Data")
                }

                is MainViewModel.MainStatus.MainSuccess -> {
                    Timber.d("The Follower Post is Come")
                    val postData = postStatus.data as ArrayList<FirebasePostData>
                    postAdapter.data = postData
                }

                is MainViewModel.MainStatus.MainError -> {
                    Timber.d("The Follower post Data is Null")
                    Toast.makeText(
                        this,
                        "the Follower Post is Null Please Check The Data",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })

        binding.recyclerviewContent.adapter = postAdapter

        viewModel.setFollowerLiveData.observe(this, Observer { followerStatus ->
            when (followerStatus) {
                is MainViewModel.MainStatus.MainLoading -> {
                    Timber.d("Loading for get Follow the User")
                }

                is MainViewModel.MainStatus.MainSuccess -> {
                    val userId = followerStatus.data as String
                    Toast.makeText(this, "Success to Follower $userId", Toast.LENGTH_SHORT).show()
                }

                is MainViewModel.MainStatus.MainError -> {
                    val message = followerStatus.message
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
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

        viewModel.postLiveData.observe(this, Observer { postStatus ->
            when (postStatus) {
                is MainViewModel.MainStatus.MainLoading -> {
                    Timber.d("Loading for post Data")
                }

                is MainViewModel.MainStatus.MainSuccess -> {
                    Toast.makeText(this, "${postStatus.data} to Post Data", Toast.LENGTH_SHORT)
                        .show()
                }

                is MainViewModel.MainStatus.MainError -> {
                    Timber.d("Error to Post Data")
                    Toast.makeText(this, "ERROR to Post Data", Toast.LENGTH_SHORT).show()
                }
            }
        })

    }

    private fun setPostSnsBottomSheet() {
        val bottomSheet: PostSnsBottomSheet = PostSnsBottomSheet {
            if (it.content == null) {
                return@PostSnsBottomSheet
            }
            viewModel.postSns(it, getCurrentTimeInMillis())
            Toast.makeText(this, getString(R.string.msg_post_sns), Toast.LENGTH_SHORT).show()
        }
        bottomSheet.show(supportFragmentManager, bottomSheet.tag)
    }

    private fun getCurrentTimeInMillis(): Long {
        return System.currentTimeMillis()
    }
}