package com.sungil.runningproejct_mvvm.utill

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sungil.runningproejct_mvvm.dataObject.FirebasePostData
import com.sungil.runningproejct_mvvm.databinding.AdapterPostBinding

class PostAdapter() : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    var data = ArrayList<FirebasePostData>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private var setOnClickListener : SetOnClickListener  ?= null
    inner class ViewHolder(private val binding : AdapterPostBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data : FirebasePostData){
            binding.txtWriter.text= data.nickName
            binding.txtTitle.text = data.title
            binding.txtContent.text = data.content
            binding.txtTime.text = CommUtil.convertLocalTime(data.time)
            binding.txtRunData.text = data.running

            binding.layoutRoot.setOnClickListener {
                setOnClickListener?.onValueClick(data.writer)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AdapterPostBinding.inflate(LayoutInflater.from(parent.context) ,parent , false )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun setOnClickListener(listener : SetOnClickListener){
        this.setOnClickListener = listener
    }

}