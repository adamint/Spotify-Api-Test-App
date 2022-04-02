package com.adamratzman.spotifyandroidexample.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.compose.setContent
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.adamratzman.spotify.models.Track
import com.adamratzman.spotifyandroidexample.auth.guardValidSpotifyApi
import com.adamratzman.spotifyandroidexample.utils.toast
import com.skydoves.landscapist.glide.GlideImage

class TrackViewActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val tracks = guardValidSpotifyApi(classBackTo = ActionHomeActivity::class.java) { api ->
                api.search.searchTrack("Avicii").items
            }

            if (tracks != null) TrackViewPage(this, tracks)
        }
    }
}

@Composable
fun TrackViewPage(activity: BaseActivity? = null, tracks: List<Track>) {
    MaterialTheme {
        val typography = MaterialTheme.typography

        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                "Avicii search results...",
                style = typography.h3,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Button(onClick = {
                if (activity == null) return@Button
                if (activity.model.credentialStore.clear()) {
                    activity.startActivity(Intent(activity, MainActivity::class.java))
                    activity.finish()
                } else {
                    toast(activity, "Logout failed")
                }
            }) {
                Text("Logout")
            }

            TrackList(tracks)
        }
    }

}

@Preview
@Composable
fun TrackViewPagePreview() {
    TrackViewPage(tracks = listOf())
}

@Composable
private fun TrackList(tracks: List<Track>) {
    val context = LocalContext.current
    LazyColumn {
        items(
            items = tracks, itemContent = { track ->
                TrackRow(track = track, onTrackClick = {
                    toast(context, "You clicked ${track.name} - opening in spotify")
                    val browserIntent =
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(track.externalUrls.first { it.name == "spotify" }.url)
                        )
                    startActivity(context, browserIntent, null)
                })
                Divider()
            })
    }
}

@Composable
private fun TrackRow(track: Track, onTrackClick: (Track) -> Unit) {
    Row(
        modifier = Modifier
            .clickable(onClick = { onTrackClick(track) })
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        val imageModifier = Modifier
            .height(46.dp)
            .width(46.dp)
            .clip(shape = CircleShape)

        GlideImage(
            imageModel = track.album.images.firstOrNull()?.url ?: "https://picsum.photos/300/300",
            contentDescription = null,
            modifier = imageModifier
        )

        Column(modifier = Modifier.padding(start = 8.dp)) {
            Text(
                text = track.name,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h6
            )
            Text(
                text = "By ${track.artists.joinToString(", ") { it.name }}",
                style = MaterialTheme.typography.body2
            )

        }
    }
}
