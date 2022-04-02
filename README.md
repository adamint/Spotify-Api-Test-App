# Spotify Api Test App

## What is this?
This is a sample app using the awesome [Spotify-Web-Api-Kotlin](https://github.com/adamint/spotify-web-api-kotlin) 
library to interact with the Spotify API. This app also implements the user login flow via PKCE and an 
anonymous login using the client credentials flow.

### What does it do?
It showcases logging in/logging out with PKCE and implicit authorization, as well as using the api to search tracks and integrate with the on-device Spotify application.

<img width="300" alt="image" src="https://user-images.githubusercontent.com/20359921/161375063-5d2c0216-caaf-4fa5-b2dd-70871a2a1e48.png"> <img width="300" alt="image" src="https://user-images.githubusercontent.com/20359921/161375069-f793ff74-b272-46ed-9ad3-bb8c1bdd19ea.png"> <img width="300" alt="image" src="https://user-images.githubusercontent.com/20359921/161375077-5d884d81-0e28-4fbe-a043-759199273cdb.png"> <img width="300" alt="image" src="https://user-images.githubusercontent.com/20359921/161375081-694e9731-ea32-4a33-ad44-67ba2a45a634.png">


## Setup
Fortunately, there's very limited setup that you need. Please follow the below steps to set up Spotify

### Spotify Developer
1. Create Spotify Developer account at the [Spotify Developer Dashboard](https://developer.spotify.com/dashboard/applications)
2. Create app in the Spotify Developer dashboard
3. Replace existing Client ID in the build.gradle in the app folder
4. Add redirect uri "spotifyandroidplayground://spotify-auth" (implicit auth) and "spotifyandroidplayground://spotify-pkce" (pkce auth) to your app at the Spotify developer website (edit settings > redirect uri)
5. Add package name "com.adamratzman.spotifyplayground" to your app at the Spotify developer website. Make sure to also add your keystore hash, which can be retrieved from either your keystore, or generated from your computer's keystore (Windows: keytool -list -v -keystore c:\users\YOUR_USER_NAME\.android\debug.keystore -alias androiddebugkey -storepass android -keypass android)
6. Spotify should now work in your app :)

#### How to allow receiving broadcasts:
On your Spotify app, go to account settings, and select the "Allow broadcasting" setting
