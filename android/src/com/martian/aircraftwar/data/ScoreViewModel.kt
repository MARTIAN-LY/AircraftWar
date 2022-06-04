package com.martian.aircraftwar.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/** 真正操作数据库时，用ViewModel，
 *  在新线程中进行操作，
 *  并及时通知视图内容的变更
 * */
class ScoreViewModel(application: Application) : AndroidViewModel(application) {

    val allData: LiveData<List<Score>>
    private val scoreDao: ScoreDao

    init {
        scoreDao = ScoreDatabase.getDatabase(application.applicationContext).getScoreDao()
        allData = scoreDao.readAllData()
    }

    /** 开启新线程，添加数据 */
    fun addScore(score: Score) {
        viewModelScope.launch(Dispatchers.IO) {
            scoreDao.addScore(score)
        }
    }

    /** 删除数据 */
    fun deleteScore(score: Score) {
        viewModelScope.launch(Dispatchers.IO) {
            scoreDao.deleteScore(score)
        }
    }


}