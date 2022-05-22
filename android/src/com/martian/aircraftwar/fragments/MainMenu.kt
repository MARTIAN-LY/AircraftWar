package com.martian.aircraftwar.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.martian.aircraftwar.R
import com.martian.aircraftwar.shop.ShopActivity
import com.martian.aircraftwar.databinding.FragmentMainMenuBinding

class MainMenu : Fragment() {

    private var _binding: FragmentMainMenuBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMainMenuBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        binding.buttonStartGame.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.nav_mainToModeSelect)
        }

        binding.buttonOnlinePK.setOnClickListener {
            Toast.makeText(activity, "功能正在开发中，敬请期待！！！", Toast.LENGTH_SHORT).show()
        }

        binding.buttonPropShop.setOnClickListener {
            Toast.makeText(activity, "功能正在开发中，敬请期待！！！", Toast.LENGTH_SHORT).show()
        }

        binding.buttonRankingList.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.nav_menuToRank)
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}