package com.martian.aircraftwar.rank

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.martian.aircraftwar.rank.local.MyScoreFragment
import com.martian.aircraftwar.rank.world.WorldRankFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, val lifeCycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifeCycle) {

    override fun getItemCount() = 2

    override fun createFragment(position: Int) = when (position) {
        0 -> MyScoreFragment()
        else -> WorldRankFragment()
    }


}