package com.martian.aircraftwar.rank.world

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.martian.aircraftwar.databinding.FragmentWorldRankBinding


/** 世界排行 Fragment*/
class WorldRankFragment : Fragment() {

    private var _binding: FragmentWorldRankBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentWorldRankBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}