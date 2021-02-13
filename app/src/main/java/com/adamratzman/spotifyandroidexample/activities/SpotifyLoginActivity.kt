package com.adamratzman.spotifyandroidexample.activities

import android.content.Intent
import android.util.Log
import com.adamratzman.spotify.SpotifyImplicitGrantApi
import com.adamratzman.spotify.SpotifyScope
import com.adamratzman.spotify.auth.AbstractSpotifyLoginActivity
import com.adamratzman.spotifyandroidexample.BuildConfig
import com.adamratzman.spotifyandroidexample.SpotifyPlaygroundApplication
import com.adamratzman.spotifyandroidexample.toast

class SpotifyLoginActivity : AbstractSpotifyLoginActivity() {
    override val state: Int = 1337
    override val clientId: String = BuildConfig.SPOTIFY_CLIENT_ID
    override val redirectUri: String = BuildConfig.SPOTIFY_REDIRECT_URI

    override fun getRequestingScopes(): List<SpotifyScope> = SpotifyScope.values().toList()

    override fun onSuccessfulAuthentication(spotifyApi: SpotifyImplicitGrantApi) {
        val model = (application as SpotifyPlaygroundApplication).model
        model.spotifyApi = spotifyApi
        toast("Authentication has completed. Launching TrackViewActivity..")

        startActivity(Intent(this, ActionHomeActivity::class.java))
        finish()
    }

    override fun onAuthenticationFailed(errorMessage: String) {
        toast("Auth failed: $errorMessage")
    }
}