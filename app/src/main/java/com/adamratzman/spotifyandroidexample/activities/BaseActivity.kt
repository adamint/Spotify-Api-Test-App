package com.adamratzman.spotifyandroidexample.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.adamratzman.spotify.SpotifyException
import com.adamratzman.spotify.auth.startSpotifyLoginActivity
import com.adamratzman.spotifyandroidexample.SpotifyPlaygroundApplication
import com.adamratzman.spotifyandroidexample.data.Model

abstract class BaseActivity : AppCompatActivity() {
    lateinit var model: Model

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = (application as SpotifyPlaygroundApplication).model
    }

    fun <T> guardValidSpotifyApi(clazz: Class<out BaseActivity>, block: () -> T): T? {
        return try {
            block()
        } catch (e: SpotifyException.ReAuthenticationNeededException) {
            activityBack = clazz
            startSpotifyLoginActivity<SpotifyLoginActivity>()
            null
        }
    }
    companion object {
        var activityBack: Class<out BaseActivity>? = null
    }
}
