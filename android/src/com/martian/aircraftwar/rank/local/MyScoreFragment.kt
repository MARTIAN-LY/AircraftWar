package com.martian.aircraftwar.rank.local

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.martian.aircraftwar.data.ScoreViewModel
import com.martian.aircraftwar.databinding.FragmentMyScoreBinding
import com.martian.aircraftwar.rank.decoration.TopSpacingItemDecoration

/** 我的得分 Fragment */
class MyScoreFragment : Fragment() {

    private var _binding: FragmentMyScoreBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: MyScoreAdapter
    private lateinit var model: ScoreViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMyScoreBinding.inflate(layoutInflater, container, false)
        adapter = MyScoreAdapter()

        /** 用 SharedPreferences获取用户名 */
        val preferences = activity?.getSharedPreferences("userInfo", AppCompatActivity.MODE_PRIVATE)
        val name = preferences?.getString("username", "")

        /** 给 RecyclerView 传递数据 */
        model = ViewModelProvider(this)[ScoreViewModel::class.java]
        model.allData.observe(viewLifecycleOwner, Observer { list ->
            adapter.setData(name, list)
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

}