package com.adamratzman.spotifyandroidexample.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adamratzman.spotify.models.Track
import com.adamratzman.spotifyandroidexample.toast
import dev.chrisbanes.accompanist.glide.GlideImage
import kotlinx.coroutines.runBlocking
import androidx.core.content.ContextCompat.startActivity

import android.net.Uri
import androidx.core.content.ContextCompat


class ActionHomeActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ActionHomeViewPage(this)
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
                activity?.model?.preferences?.spotifyAccessToken = activity?.model?.preferences?.spotifyAccessToken?.copy(accessToken = "f")
               activity?.let { toast(it, message = "Invalidated spotify token... next call should refresh api") }
            }) {
                Text("Invalidate token")
            }
        }
    }
}

@Preview
@Composable
fun ActionHomeViewPagePreview() {
    ActionHomeViewPage()
}