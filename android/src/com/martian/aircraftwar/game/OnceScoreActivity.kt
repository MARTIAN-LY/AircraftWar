package com.martian.aircraftwar.game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.martian.aircraftwar.MainActivity
import com.martian.aircraftwar.data.Score
import com.martian.aircraftwar.data.ScoreViewModel
import com.martian.aircraftwar.data.TmpScore
import com.martian.aircraftwar.databinding.ActivityOnceScoreBinding
import com.martian.aircraftwar.rank.RankActivity

class OnceScoreActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnceScoreBinding
    private lateinit var viewModel: ScoreViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnceScoreBinding.inflate(layoutInflater)

        setContentView(binding.root)

        /** 获取ViewModel 操作数据库 */
        viewModel = ViewModelProvider(this)[ScoreViewModel::class.java]

        val mode = when (TmpScore.mode) {
            0 -> "简单模式"
            1 -> "普通模式"
            else -> "困难模式"
        }

        /** 显示本局得分 */
        binding.textMode.text = mode
        binding.textScore.text = TmpScore.score.toString()

        /** 点击任意一个 button，都会将数据保存到本地 */

        /** 去 难度选择 界面*/
        binding.btnAgain.setOnClickListener {
            viewModel.addScore(Score(0, TmpScore.score, TmpScore.mode, TmpScore.date))
            startActivity(Intent(this, MainActivity::class.java))
        }
        /** 去 排行榜 界面*/
        binding.btnConfirm.setOnClickListener {
            viewModel.addScore(Score(0, TmpScore.score, TmpScore.mode, TmpScore.date))
            val intent = Intent(this, RankActivity::class.java)
            intent.putExtra("fromMenu", false)
            startActivity(intent)
            finish()
        }
        /** 去 首页 */
        binding.btnFloat1.setOnClickListener {
            viewModel.addScore(Score(0, TmpScore.score, TmpScore.mode, TmpScore.date))
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("back", true)
            startActivity(intent)

        }

    }
}