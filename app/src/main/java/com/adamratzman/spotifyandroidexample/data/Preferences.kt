package com.adamratzman.spotifyandroidexample.data

import android.annotation.SuppressLint
import android.util.Log
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.adamratzman.spotify.models.Token
import com.adamratzman.spotifyandroidexample.SpotifyPlaygroundApplication
import com.adamratzman.spotifyandroidexample.saveLong
import com.adamratzman.spotifyandroidexample.saveString
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Preferences(val app: SpotifyPlaygroundApplication) {
    @Suppress("DEPRECATION")
    private val encryptedPreferences = EncryptedSharedPreferences
        .create(
            "preferences",
            MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
            app,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

    var spotifyTokenExpiry: Long?
        get() {
            val expiry = encryptedPreferences.getLong(Constants.SPOTIFY_TOKEN_EXPIRY, -1)
            return if (expiry == -1L) null else expiry
        }
        set(value) {
            if (value == null) encryptedPreferences.edit().remove(Constants.SPOTIFY_TOKEN_EXPIRY)
                .apply()
            else encryptedPreferences.saveLong(Constants.SPOTIFY_TOKEN_EXPIRY, value)
        }

    var spotifyAccessToken: Token?
        get() {
            val token = encryptedPreferences.getString(Constants.SPOTIFY_ACCESS_TOKEN, null)
                ?.let { Log.i("spotify-app", "token is: $it");Json.decodeFromString<Token>(it) }

            return when {
                token == null -> null
                spotifyTokenExpiry == null || spotifyTokenExpiry!! < System.currentTimeMillis() -> null
                else -> token
            }
        }
        set(token) {
            if (token == null) encryptedPreferences.edit().remove(Constants.SPOTIFY_ACCESS_TOKEN)
                .apply()
            else encryptedPreferences.saveString(
                Constants.SPOTIFY_ACCESS_TOKEN,
                Json.encodeToString(token)
            )
        }

    @SuppressLint("ApplySharedPref")
    fun clear(): Boolean = try {
        encryptedPreferences.edit().clear().commit()
    } catch (e: Exception) {
        // This might crash, encrypted preferences is still alpha...
        false
    }
}