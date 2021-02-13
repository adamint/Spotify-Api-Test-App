# Spotify Api Test App

## What is this?
This is a sample app using the awesome [Spotify-Web-Api-Kotlin](https://github.com/adamint/spotify-web-api-kotlin) library to interact with the Spotify API. This app also implements the user login flow via PKCE and an anonymous login using the client credentials flow.

## Setup
Because I won't provide the internet with my Spotify Client ID and Client Secret, you need to enter them yourself. Follow the steps below to get the required credentials and eventually get a working app:

### Spotify Developer
1. Create Spotify Developer account at the [Spotify Developer Dashboard](https://developer.spotify.com/dashboard/applications)
2. Create app in the Spotify Developer dashboard
3. Copy Client ID and Client Secret to the build.gradle in the app folder (replace the text "REPLACE_THIS" with the corresponding value)
4. Add redirect uri "spotifyplayground://spotify" to your app at the Spotify developer website (edit settings > redirect uri)
5. Add package name "dev.nielsg.spotifyplayground" to your app at the Spotify developer website. Make sure to also add your keystore hash, which can be retrieved from either your keystore, or generated from your computer's keystore (Windows: keytool -list -v -keystore c:\users\YOUR_USER_NAME\.android\debug.keystore -alias androiddebugkey -storepass android -keypass android)
6. Spotify should now work in your app :)


