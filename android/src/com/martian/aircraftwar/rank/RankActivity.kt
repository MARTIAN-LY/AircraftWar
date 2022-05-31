package com.martian.aircraftwar.rank

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.martian.aircraftwar.data.TmpScore
import com.martian.aircraftwar.databinding.ActivityRankBinding
import java.io.IOException

class RankActivity : AppCompatActivity() {

    private lateinit var rankingDao:RankingDao;
    private lateinit var binding: ActivityRankBinding
    companion object{
        lateinit var name:String;
    }
    override fun onCreate(savedInstanceState: Bundle?) {
//        rankingDao = RankingDao();
//        if(TmpScore.score != -1)
//        {
//            rankingDao.doAdd(Ranking(name, TmpScore.score, TmpScore.time));
//            try {
//                rankingDao.saveToFile()
//            } catch (ex: IOException) {
//                ex.printStackTrace()
//            }
//        }
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
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position))
            }
        })

    }
}