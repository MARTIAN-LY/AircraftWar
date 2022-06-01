package com.martian.aircraftwar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.martian.aircraftwar.databinding.FragmentAddBinding
import com.martian.aircraftwar.databinding.FragmentMainMenuBinding

class AddFragment : Fragment() {
    private var _binding: FragmentAddBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}