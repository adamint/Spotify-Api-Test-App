package com.adamratzman.spotifyandroidexample.activities

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adamratzman.spotify.auth.startSpotifyLoginActivity

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
                activity?.startSpotifyLoginActivity<SpotifyLoginActivity>()
            }) {
                Text("Connect to Spotify (spotify-auth integration)")
            }

            Text("This example authenticates via the spotify-auth library", style = typography.body2)
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
