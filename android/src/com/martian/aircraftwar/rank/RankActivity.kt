package com.martian.aircraftwar.rank

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.martian.aircraftwar.R
import com.martian.aircraftwar.data.TmpScore
import com.martian.aircraftwar.databinding.ActivityRankBinding


class RankActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRankBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRankBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //设置ViewPager
        val adapter = ViewPagerAdapter()
        binding.viewPager.adapter = adapter

        // 把 ViewPager 和 TabLayout 关联
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "我的得分"
                else -> tab.text = "世界排行"
            }
        }.attach()

        //TabLayout点击事件
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                binding.viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position))
            }
        })

//        showLayoutDialog()
    }

//    private fun showLayoutDialog() {
//        //加载布局并初始化组件
//        val alertDialog = LayoutInflater.from(this).inflate(R.layout.alert_round_score,null)
//        val textScore = alertDialog.findViewById<TextView>(R.id.text_score)
//        val button = alertDialog.findViewById<Button>(R.id.btn_confirm)
//
//        textScore.setText(TmpScore.score.toString())
//
//        val layoutDialog: AlertDialog.Builder = AlertDialog.Builder(this)
//        layoutDialog.setView(alertDialog)
//
//
//        button.setOnClickListener {
//            layoutDialog.setOnDismissListener {
//                DialogInterface.OnDismissListener { dialog -> dialog.dismiss() }
//            }
//        }
//
//        layoutDialog.create().show()
//    }
}