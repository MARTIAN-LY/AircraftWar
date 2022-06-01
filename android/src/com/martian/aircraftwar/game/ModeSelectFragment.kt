package com.martian.aircraftwar.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.martian.aircraftwar.databinding.FragmentModeSelectBinding

class ModeSelectFragment : Fragment() {

    private var _binding: FragmentModeSelectBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentModeSelectBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        //返回按钮
        binding.buttonBack.setOnClickListener {
            Navigation.findNavController(view).popBackStack()
        }

        //不同难度
        binding.buttonEasy.setOnClickListener {
            val action = ModeSelectFragmentDirections.navModeToGame(0)
            Navigation.findNavController(view).navigate(action)
        }
        binding.buttonNormal.setOnClickListener {
            val action = ModeSelectFragmentDirections.navModeToGame(1)
            Navigation.findNavController(view).navigate(action)
        }
        binding.buttonHard.setOnClickListener {
            val action = ModeSelectFragmentDirections.navModeToGame(2)
            Navigation.findNavController(view).navigate(action)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}