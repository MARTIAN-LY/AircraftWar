package com.martian.aircraftwar.rank

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.martian.aircraftwar.R
import com.martian.aircraftwar.databinding.ItemViewPagerBinding

/** 构造方法传一个 list 是数据源*/
class ViewPagerAdapter(

) : RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {

    //ViewHolder是单个item的容器
    inner class ViewPagerViewHolder(val binding: ItemViewPagerBinding) :
        RecyclerView.ViewHolder(binding.root)

    //创建一个个的ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemViewPagerBinding.inflate(layoutInflater, parent, false)
        return ViewPagerViewHolder(binding)
    }

    //绑定数据
    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        holder.binding.apply {
            imageView2.setImageResource(R.mipmap.ic_launcher)
        }
    }

    override fun getItemCount() = 2

}