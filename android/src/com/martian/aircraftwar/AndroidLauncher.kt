package com.martian.aircraftwar

import com.badlogic.gdx.backends.android.AndroidApplication
import android.os.Bundle
import androidx.navigation.navArgs
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration
import com.martian.aircraftwar.application.AircraftWarGame

class AndroidLauncher : AndroidApplication() {

    //接收数据，指定游戏模式
    private val args: AndroidLauncherArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val config = AndroidApplicationConfiguration()
        config.useAccelerometer = false
        config.useCompass = false
        initialize(AircraftWarGame(args.mode, Communication(this)), config);
    }
}