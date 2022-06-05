package com.martian.aircraftwar.rank.local

import com.martian.aircraftwar.data.Score

interface ItemOperator {
    fun deleteData(score: Score)
}