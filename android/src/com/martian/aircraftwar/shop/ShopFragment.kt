package com.martian.aircraftwar.shop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.martian.aircraftwar.R
import com.martian.aircraftwar.databinding.FragmentMainMenuBinding
import com.martian.aircraftwar.databinding.FragmentShopBinding

class ShopFragment : Fragment() {

    private var _binding: FragmentShopBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentShopBinding.inflate(layoutInflater, container, false)

        /** 用 SharedPreference 存储当前的金币数、当前拥有的道具数，还可购买的道具数 */

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}