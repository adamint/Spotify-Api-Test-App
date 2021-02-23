package com.adamratzman.spotifyandroidexample.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adamratzman.spotify.SpotifyException
import com.adamratzman.spotifyandroidexample.auth.guardValidSpotifyApi
import com.adamratzman.spotifyandroidexample.utils.toast


class ActionHomeActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        guardValidSpotifyApi(ActionHomeActivity::class.java) { api ->
            if (!api.isTokenValid(true).isValid) throw SpotifyException.ReAuthenticationNeededException()

            setContent {
                ActionHomeViewPage(this)
            }
        }
    }
}

@Composable
fun ActionHomeViewPage(activity: BaseActivity? = null) {
    MaterialTheme {
        val typography = MaterialTheme.typography

        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                "What do you want to do?",
                style = typography.h4,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Button(onClick = {
                activity?.startActivity(Intent(activity, TrackViewActivity::class.java))
            }) {
                Text("See a track search/display example")
            }

            Spacer(modifier = Modifier.preferredHeight(16.dp))

            Button(onClick = {
                activity?.model?.credentialStore?.spotifyAccessToken = "invalid"
                activity?.let {
                    toast(
                        it,
                        message = "Invalidated spotify token... next call should refresh api"
                    )
                }
            }) {
                Text("Invalidate token")
            }

            Spacer(modifier = Modifier.preferredHeight(16.dp))

            Button(onClick = {
                activity?.startActivity(Intent(activity, ViewBroadcastsActivity::class.java))
            }) {
                Text("View Spotify app broadcasts")
            }

            Spacer(modifier = Modifier.preferredHeight(16.dp))

            Button(onClick = {
                activity?.startActivity(Intent(activity, MainActivity::class.java))
            }) {
                Text("Go home →")
            }
        }
    }
}

@Preview
@Composable
fun ActionHomeViewPagePreview() {
    ActionHomeViewPage()
}