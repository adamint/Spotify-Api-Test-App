package com.adamratzman.spotifyandroidexample.notifications

import com.adamratzman.spotify.notifications.AbstractSpotifyBroadcastReceiver
import com.adamratzman.spotify.notifications.SpotifyMetadataChangedData
import com.adamratzman.spotify.notifications.SpotifyPlaybackStateChangedData
import com.adamratzman.spotify.notifications.SpotifyQueueChangedData
import com.adamratzman.spotifyandroidexample.activities.ViewBroadcastsActivity

class SpotifyBroadcastReceiver(val activity: ViewBroadcastsActivity) : AbstractSpotifyBroadcastReceiver() {
    override fun onMetadataChanged(data: SpotifyMetadataChangedData) {
        activity.broadcasts += data
        println("broadcast: ${data}")
    }

    override fun onPlaybackStateChanged(data: SpotifyPlaybackStateChangedData) {
        activity.broadcasts += data
        println("broadcast: $data")
    }

    override fun onQueueChanged(data: SpotifyQueueChangedData) {
        activity.broadcasts += data
        println("broadcast: $data")
    }
}