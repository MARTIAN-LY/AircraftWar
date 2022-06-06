package com.martian.aircraftwar.rank


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.martian.aircraftwar.shop.MainActivity
import com.martian.aircraftwar.databinding.ActivityRankBinding


class RankActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRankBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityRankBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /** 返回主界面 */
        binding.btnFloat2.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            val fromMenu = this.intent.getBooleanExtra("fromMenu",true)
            if (fromMenu){
                intent.putExtra("back",false)
            } else{
                intent.putExtra("back",true)
            }
            startActivity(intent)
        }

        //设置ViewPager
        binding.viewPager.adapter = ViewPagerAdapter(this.supportFragmentManager, lifecycle)

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
    }

}