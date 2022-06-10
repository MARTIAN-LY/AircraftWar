package com.martian.aircraftwar.game

import com.badlogic.gdx.backends.android.AndroidApplication
import android.os.Bundle
import androidx.navigation.navArgs
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration
import com.martian.aircraftwar.application.AircraftWarGame

class AndroidLauncher : AndroidApplication() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val config = AndroidApplicationConfiguration()
        config.useAccelerometer = false
        config.useCompass = false

        /** 接收数据，指定模式 */
        val mode = this.intent.getIntExtra("MODE",2)

        initialize(
            AircraftWarGame(
                mode,
                Communication(this)
            ), config
        )
    }
}