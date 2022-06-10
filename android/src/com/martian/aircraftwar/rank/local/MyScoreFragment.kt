package com.martian.aircraftwar.rank.local

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.martian.aircraftwar.R
import com.martian.aircraftwar.data.Score
import com.martian.aircraftwar.data.ScoreViewModel
import com.martian.aircraftwar.databinding.FragmentMyScoreBinding
import com.martian.aircraftwar.rank.decoration.TopSpacingItemDecoration
import com.martian.aircraftwar.rank.world.MyBestScore
import java.text.FieldPosition

/** 我的得分 Fragment */
class MyScoreFragment : Fragment(), ItemOperator {

    private var _binding: FragmentMyScoreBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: MyScoreAdapter
    private lateinit var model: ScoreViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMyScoreBinding.inflate(layoutInflater, container, false)

        adapter = MyScoreAdapter(MyBestScore.name, this)

        /** 给 RecyclerView 传递数据 */
        model = ViewModelProvider(this)[ScoreViewModel::class.java]
        model.allData.observe(viewLifecycleOwner, Observer { list ->
            adapter.setData(list)
        })

        /** 设置 RecyclerView */
        binding.recyclerviewMyscore.layoutManager = LinearLayoutManager(activity)
        binding.recyclerviewMyscore.addItemDecoration(TopSpacingItemDecoration(30))
        binding.recyclerviewMyscore.adapter = adapter

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun deleteData(score: Score) {
        this.activity?.let {
            val dialog = AlertDialog.Builder(it)
                .setTitle("删除")
                .setMessage("确定删除该条记录吗?")
                .setIcon(R.drawable.ic_delete)
                .setNegativeButton("取消") { _, _ ->
                    Toast.makeText(it, "取消删除", Toast.LENGTH_SHORT).show()
                }
                .setPositiveButton("确定") { _, _ ->
                    model.deleteScore(score)
                    Toast.makeText(it, "删除了一条记录", Toast.LENGTH_SHORT).show()
                }
            dialog.show()
        }

    }

}