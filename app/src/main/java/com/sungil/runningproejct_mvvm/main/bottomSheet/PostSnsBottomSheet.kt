package com.sungil.runningproejct_mvvm.main.bottomSheet

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sungil.runningproejct_mvvm.R
import com.sungil.runningproejct_mvvm.dataObject.PostSnsBottomSheet
import com.sungil.runningproejct_mvvm.databinding.BottomSheetPostSnsBinding
import timber.log.Timber

class PostSnsBottomSheet(val value: (PostSnsBottomSheet) -> Unit) : BottomSheetDialogFragment() {

    private lateinit var binding: BottomSheetPostSnsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = BottomSheetPostSnsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addListener()
    }

    override fun onStart() {
        super.onStart()
        dialog?.setCancelable(false)
    }

    private fun addListener() {
        binding.imgClose.setOnClickListener {
            dialog?.dismiss()
        }

        binding.btnPostSns.setOnClickListener {
            Timber.d("User Select Post SNS")
            val title = binding.txtContentTitle.text.toString().trim()
            val content = binding.txtContent.text.toString().trim()
            val km = binding.txtKm.text.toString().trim()

            if (title == "") {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.msg_check_content),
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            if (content == "") {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.msg_check_content),
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            if (km == "") {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.msg_check_km),
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            val postData = PostSnsBottomSheet(title, content, km)
            value(postData)
            dialog?.dismiss()
        }
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        val postData = PostSnsBottomSheet(null, null, null)
        value(postData)
        dialog.dismiss()
    }

    override fun getTheme(): Int {
        return R.style.DialogCustomTheme
    }

}