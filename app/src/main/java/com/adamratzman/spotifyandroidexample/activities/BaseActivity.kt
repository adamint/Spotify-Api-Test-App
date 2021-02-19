package com.adamratzman.spotifyandroidexample.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.adamratzman.spotifyandroidexample.SpotifyPlaygroundApplication
import com.adamratzman.spotifyandroidexample.data.Model

abstract class BaseActivity : AppCompatActivity() {
    lateinit var model: Model

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = (application as SpotifyPlaygroundApplication).model
    }
}
