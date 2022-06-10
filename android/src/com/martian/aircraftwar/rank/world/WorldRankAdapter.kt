package com.martian.aircraftwar.rank.world

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.martian.aircraftwar.databinding.RecyclerMyscoreBinding
import com.martian.aircraftwar.databinding.RecyclerWorldRankBinding

/** 世界排行界面 RecyclerView 的 Adapter*/
class WorldRankAdapter(private val list: ArrayList<Data>) :
    RecyclerView.Adapter<WorldRankAdapter.MyViewHolder>() {

    /** ViewHolder 是单个 Item 的容器*/
    inner class MyViewHolder(val binding: RecyclerWorldRankBinding) :
        RecyclerView.ViewHolder(binding.root)

    /** 创建 ViewHolder */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RecyclerWorldRankBinding.inflate(layoutInflater, parent, false)
        return MyViewHolder(binding)
    }

    /** 绑定数据 */
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = list[position]
        holder.binding.apply {
            itemWorldrankTextRank.text = (position + 1).toString()
            itemWorldrankTextName.text = currentItem.name
            itemWorldrankTextScore.text = currentItem.score.toString()
        }
    }

    override fun getItemCount() = list.size
}