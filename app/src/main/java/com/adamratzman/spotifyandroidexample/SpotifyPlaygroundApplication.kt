package com.adamratzman.spotifyandroidexample

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.adamratzman.spotifyandroidexample.data.Model

class SpotifyPlaygroundApplication : Application() {
    lateinit var model: Model

    override fun onCreate() {
        super.onCreate()
        model = Model(this)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
    }
}