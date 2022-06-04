package com.martian.aircraftwar.game

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.martian.aircraftwar.databinding.FragmentModeSelectBinding

class ModeSelectFragment : Fragment() {

    private var _binding: FragmentModeSelectBinding? = null
    private val binding get() = _binding!!
    private var needBack = true

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

    override fun onResume() {
        super.onResume()
        //如果收到返回首页的请求，自动返回首页
        val back = activity?.intent?.getBooleanExtra("back", false)
        Log.e("================", back.toString())
        if (back == true) {
            activity?.intent?.putExtra("back", false)
            Navigation.findNavController(binding.root).popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}