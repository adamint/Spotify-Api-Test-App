package com.adamratzman.spotifyandroidexample.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adamratzman.spotify.auth.implicit.startSpotifyImplicitLoginActivity
import com.adamratzman.spotify.auth.pkce.startSpotifyClientPkceLoginActivity
import com.adamratzman.spotifyandroidexample.auth.SpotifyImplicitLoginActivityImpl
import com.adamratzman.spotifyandroidexample.auth.SpotifyPkceLoginActivityImpl

class MainActivity : AppCompatActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AuthPage(this)
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun AuthPage(activity: Activity? = null) {
    MaterialTheme {
        val typography = MaterialTheme.typography

        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                "Log in to Spotify below...",
                style = typography.h3,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Button(onClick = {
                activity?.startSpotifyImplicitLoginActivity<SpotifyImplicitLoginActivityImpl>()
            }) {
                Text("Connect to Spotify (spotify-auth integration, Implicit Grant)")
            }

            Text(
                "The button above starts authentication via the spotify-auth library",
                style = typography.body2
            )

            Button(onClick = {
                activity?.startSpotifyClientPkceLoginActivity(SpotifyPkceLoginActivityImpl::class.java)
            }) {
                Text("Connect to Spotify (spotify-web-api-kotlin integration, PKCE auth)")
            }

            Text(
                "The button above starts authentication via our PKCE auth implementation",
                style = typography.body2
            )

            Button(onClick = {
                activity?.startActivity(Intent(activity, ActionHomeActivity::class.java))
            }) {
                Text("Go to the app →")
            }
            Text(
                "If you are logged out when clicking this button, you will be prompted to authenticate via spotify-auth via implicit auth, if you haven't already authenticated via PKCE",
                style = typography.body2
            )

            Spacer(modifier = Modifier.preferredHeight(16.dp))

            Text("spotify-web-api-kotlin by Adam Ratzman", style = typography.body2)
        }
    }

}

@ExperimentalAnimationApi
@Preview
@Composable
fun AuthPagePreview() {
    AuthPage()
}

/*
@Composable
fun NewsStory() {
    val image = imageResource(R.drawable.header)
    MaterialTheme {
        val typography = MaterialTheme.typography

        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            val imageModifier = Modifier
                .preferredHeight(180.dp)
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(4.dp))


            Image(
                image,
                null,
                modifier = imageModifier,
                contentScale = ContentScale.Crop
            )

            Spacer(Modifier.preferredHeight(16.dp))

            Text(
                "A day in Shark Fin Cove",
                style = typography.h6,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text("Davenport, California", style = typography.body2)
            Text("December 2018", style = typography.body2)
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    NewsStory()
}
*/
