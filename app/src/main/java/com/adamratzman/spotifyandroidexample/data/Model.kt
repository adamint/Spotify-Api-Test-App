package com.adamratzman.spotifyandroidexample.data

import com.adamratzman.spotify.auth.SpotifyDefaultCredentialStore
import com.adamratzman.spotifyandroidexample.BuildConfig
import com.adamratzman.spotifyandroidexample.SpotifyPlaygroundApplication

class Model {
    val credentialStore by lazy {
        SpotifyDefaultCredentialStore(
            BuildConfig.SPOTIFY_CLIENT_ID,
            SpotifyPlaygroundApplication.context
        )
    }
}