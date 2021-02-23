package com.adamratzman.spotifyandroidexample.auth

import android.app.Activity
import com.adamratzman.spotify.SpotifyClientApi
import com.adamratzman.spotify.SpotifyException
import com.adamratzman.spotify.auth.SpotifyDefaultCredentialStore
import com.adamratzman.spotify.auth.implicit.startSpotifyImplicitLoginActivity
import com.adamratzman.spotify.auth.pkce.startSpotifyClientPkceLoginActivity
import com.adamratzman.spotifyandroidexample.data.Model
import kotlinx.coroutines.runBlocking

fun <T> Activity.guardValidSpotifyApi(
    classBackTo: Class<out Activity>,
    alreadyTriedToReauthenticate: Boolean = false,
    block: suspend (api: SpotifyClientApi) -> T
): T? {
    return runBlocking {
        try {
            val token = Model.credentialStore.spotifyToken
                ?: throw SpotifyException.ReAuthenticationNeededException()
            val usesPkceAuth = token.refreshToken != null
            val api = (if (usesPkceAuth) Model.credentialStore.getSpotifyClientPkceApi()
            else Model.credentialStore.getSpotifyImplicitGrantApi())
                ?: throw SpotifyException.ReAuthenticationNeededException()

            block(api)
        } catch (e: SpotifyException) {
            e.printStackTrace()
            val usesPkceAuth = Model.credentialStore.spotifyToken?.refreshToken != null
            if (usesPkceAuth) {
                val api = Model.credentialStore.getSpotifyClientPkceApi()!!
                if (!alreadyTriedToReauthenticate) {
                    try {
                        api.refreshToken()
                        Model.credentialStore.spotifyToken = api.token
                        block(api)
                    } catch (e: SpotifyException.ReAuthenticationNeededException) {
                        e.printStackTrace()
                        return@runBlocking guardValidSpotifyApi(
                            classBackTo = classBackTo,
                            alreadyTriedToReauthenticate = true,
                            block = block
                        )
                    } catch (e: IllegalArgumentException) {
                        e.printStackTrace()
                        return@runBlocking guardValidSpotifyApi(
                            classBackTo = classBackTo,
                            alreadyTriedToReauthenticate = true,
                            block = block
                        )
                    }
                } else {
                    pkceClassBackTo = classBackTo
                    startSpotifyClientPkceLoginActivity(SpotifyPkceLoginActivityImpl::class.java)
                    null
                }
            } else {
                SpotifyDefaultCredentialStore.activityBackOnImplicitAuth = classBackTo
                startSpotifyImplicitLoginActivity(SpotifyImplicitLoginActivityImpl::class.java)
                null
            }
        }
    }
}
