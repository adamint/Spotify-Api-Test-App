# Spotify Api Test App

## What is this?
This is a sample app using the awesome [Spotify-Web-Api-Kotlin](https://github.com/adamint/spotify-web-api-kotlin) 
library to interact with the Spotify API. This app also implements the user login flow via PKCE and an 
anonymous login using the client credentials flow.

## Setup
Fortunately, there's very limited setup that you need. Please follow the below steps to set up Spotify

### Spotify Developer
1. Create Spotify Developer account at the [Spotify Developer Dashboard](https://developer.spotify.com/dashboard/applications)
2. Create app in the Spotify Developer dashboard
3. Replace existing Client ID in the build.gradle in the app folder
4. Add redirect uri "spotifyandroidplayground://spotify-auth" (implicit auth) and "spotifyandroidplayground://spotify-pkce" (pkce auth) to your app at the Spotify developer website (edit settings > redirect uri)
5. Add package name "com.adamratzman.spotifyplayground" to your app at the Spotify developer website. Make sure to also add your keystore hash, which can be retrieved from either your keystore, or generated from your computer's keystore (Windows: keytool -list -v -keystore c:\users\YOUR_USER_NAME\.android\debug.keystore -alias androiddebugkey -storepass android -keypass android)
6. Spotify should now work in your app :)



