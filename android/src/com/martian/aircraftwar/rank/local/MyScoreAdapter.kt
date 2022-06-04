package com.martian.aircraftwar.rank.local

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.martian.aircraftwar.data.Score
import com.martian.aircraftwar.databinding.RecyclerMyscoreBinding

/** 我的得分界面 RecyclerView 的 Adapter*/
class MyScoreAdapter(
    private val name: String?,
    private val operator: ItemOperator
) : RecyclerView.Adapter<MyScoreAdapter.MyViewHolder>() {


    private var scoreList: List<Score> = emptyList()

    /** ViewHolder 是单个 item 的容器*/
    inner class MyViewHolder(val binding: RecyclerMyscoreBinding) :
        RecyclerView.ViewHolder(binding.root)

    /** 创建 ViewHolder */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RecyclerMyscoreBinding.inflate(layoutInflater, parent, false)
        return MyViewHolder(binding)
    }

    /** 绑定数据 */
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = scoreList[position]
        val rank = (position + 1).toString()
        holder.binding.apply {
            itemMyscoreTextRank.text = rank
            itemMyscoreTextScore.text = currentItem.num.toString()
            itemMyscoreTextName.text = name
            itemMyscoreTextTime.text = currentItem.date
            itemMyscoreTextMode.text = when (currentItem.mode) {
                0 -> "简单"
                1 -> "普通"
                else -> "困难"
            }

            itemMyscoreBtnDelete.setOnClickListener {
                operator.deleteData(currentItem)
            }
        }
    }

    /** item 个数 ,最多 20 个 */
    override fun getItemCount() = if (scoreList.size > 20) {
        20
    } else {
        scoreList.size
    }

    fun setData(list: List<Score>) {
        this.scoreList = list
        notifyDataSetChanged()
    }

}