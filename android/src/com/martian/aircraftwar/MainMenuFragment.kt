package com.martian.aircraftwar

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.martian.aircraftwar.databinding.FragmentMainMenuBinding
import com.martian.aircraftwar.rank.RankActivity

class MainMenuFragment : Fragment() {

    private var _binding: FragmentMainMenuBinding? = null
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
            Navigation.findNavController(view).navigate(R.id.nav_menu_to_online)
            Toast.makeText(activity, "功能正在开发中，敬请期待！！！", Toast.LENGTH_SHORT).show()
        }

        binding.buttonPropShop.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.nav_menu_to_shop)
            Toast.makeText(activity, "功能正在开发中，敬请期待！！！", Toast.LENGTH_SHORT).show()
        }

        /** 打开排行榜 */
        binding.buttonRankingList.setOnClickListener {
            val intent = Intent(activity, RankActivity::class.java)
            intent.putExtra("fromMenu",true)
            startActivity(intent)
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}