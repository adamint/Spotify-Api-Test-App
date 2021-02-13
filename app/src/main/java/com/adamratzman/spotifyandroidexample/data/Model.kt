package com.adamratzman.spotifyandroidexample.data

import com.adamratzman.spotify.GenericSpotifyApi
import com.adamratzman.spotify.SpotifyImplicitGrantApi
import com.adamratzman.spotify.models.Token
import com.adamratzman.spotify.spotifyImplicitGrantApi
import com.adamratzman.spotifyandroidexample.BuildConfig
import com.adamratzman.spotifyandroidexample.SpotifyPlaygroundApplication

class Model(val app: SpotifyPlaygroundApplication) {
    val preferences = Preferences(app)
    lateinit var authRefreshTokenProducer: suspend (GenericSpotifyApi) -> Token

    var spotifyApi: SpotifyImplicitGrantApi?
        get() {
            val token = preferences.spotifyAccessToken ?: return null
            return spotifyImplicitGrantApi(
                BuildConfig.SPOTIFY_CLIENT_ID,
                token
            ) {
                if (::authRefreshTokenProducer.isInitialized) refreshTokenProducer =
                    authRefreshTokenProducer
            }
        }
        set(value) {
            preferences.spotifyAccessToken = value?.token
            preferences.spotifyTokenExpiry = value?.token?.expiresAt
        }
}