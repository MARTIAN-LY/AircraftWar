package com.martian.aircraftwar.rank.world

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.martian.aircraftwar.data.ScoreViewModel
import com.martian.aircraftwar.databinding.FragmentWorldRankBinding
import com.martian.aircraftwar.online.Client
import com.martian.aircraftwar.rank.decoration.TopSpacingItemDecoration
import java.util.*


/** 世界排行 Fragment*/
class WorldRankFragment : Fragment() {

    private var _binding: FragmentWorldRankBinding? = null
    private val binding get() = _binding!!
    private val handler = Handler();
    private lateinit var model: ScoreViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentWorldRankBinding.inflate(layoutInflater, container, false)


        /** 尝试连接服务器 */
        Thread {
            Log.e("========", "尝试获取世界排行")
            Client.initDataConnection()
        }.start()

        /** 更新 UI */
        Thread {
            Thread.sleep(2000)
            if (Client.dataIsConnected()) {

                Client.sendUserMessage(Data(MyBestScore.name,MyBestScore.num))
                val list = Client.getUserMessage()

                /** 获取到世界排名，更新UI */
                handler.post {
                    Toast.makeText(activity, "获取世界排名成功!!!", Toast.LENGTH_SHORT).show()

                    /** 设置 RecyclerView */
                    binding.recyclerviewWorld.layoutManager = LinearLayoutManager(activity)
                    binding.recyclerviewWorld.addItemDecoration(TopSpacingItemDecoration(20))
                    binding.recyclerviewWorld.adapter = WorldRankAdapter(list)

                    binding.progressBarWorld.visibility = View.INVISIBLE
                    binding.recyclerviewWorld.visibility = View.VISIBLE
                    binding.imageFailed.visibility = View.INVISIBLE
                }
                Client.closeDataSocket()

            } else {
                handler.post {
                    Toast.makeText(activity, "获取世界排名失败", Toast.LENGTH_SHORT).show()
                    binding.progressBarWorld.visibility = View.INVISIBLE
                    binding.recyclerviewWorld.visibility = View.INVISIBLE
                    binding.imageFailed.visibility = View.VISIBLE
                }
                Log.e("========", "Fragment里面获取世界排名失败")
            }

        }.start()

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}