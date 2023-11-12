//package com.spotify.musicservice.connection;
//
//
//import se.michaelthelin.spotify.SpotifyApi;
//import se.michaelthelin.spotify.SpotifyHttpManager;
//import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
//import se.michaelthelin.spotify.model_objects.credentials.AuthorizationCodeCredentials;
//import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;
//import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;
//
//import java.io.IOException;
//import java.net.URI;
//import java.util.concurrent.CancellationException;
//import java.util.concurrent.CompletableFuture;
//import java.util.concurrent.CompletionException;
//
//public class SpotifyConnection {
//
//    private static final String CLIENT_ID = "43067072551e40bba3392993d00b10ed";
//    private static final String CLIENT_SECRET = "d4bac36025b743f0b990c2c34366b859";
//    private static final URI REDIRECT_URI = SpotifyHttpManager.makeUri("https://127.0.0.1:9081/");
//
//    public static void main(String[] args) {
//        // Step 1: Generate the authorization URL
//        AuthorizationCodeUriRequest authorizationCodeUriRequest = new SpotifyApi.Builder()
//                .setClientId(CLIENT_ID)
//                .setClientSecret(CLIENT_SECRET)
//                .setRedirectUri(REDIRECT_URI)
//                .build()
//                .authorizationCodeUri()
//                .state("some-state")
//                .scope("user-read-private,user-read-email") // Add the required scopes
//                .show_dialog(true)
//                .build();
//
//        final URI uri = authorizationCodeUriRequest.execute();
//        System.out.println("Please click the following link to authorize: " + uri);
//
//        // Step 2: Retrieve the authorization code (user must click the link and authorize the app)
//
//        // Step 3: Exchange the authorization code for an access token
//        // Replace "YOUR_AUTHORIZATION_CODE" with the actual authorization code obtained from the user
//        String authorizationCode = "YOUR_AUTHORIZATION_CODE";
//
//        AuthorizationCodeRequest authorizationCodeRequest = new SpotifyApi.Builder()
//                .setClientId(CLIENT_ID)
//                .setClientSecret(CLIENT_SECRET)
//                .setRedirectUri(REDIRECT_URI)
//                .build()
//                .authorizationCode(authorizationCode)
//                .build();
//
//        CompletableFuture<AuthorizationCodeCredentials> authorizationCodeCredentialsFuture = authorizationCodeRequest.executeAsync();
//
//        // Handle the result
//        authorizationCodeCredentialsFuture.thenAcceptAsync(
//                        authorizationCodeCredentials -> {
//                            System.out.println("Access Token: " + authorizationCodeCredentials.getAccessToken());
//                            System.out.println("Refresh Token: " + authorizationCodeCredentials.getRefreshToken());
//                        })
//                .exceptionally(e -> {
//                    if (e instanceof SpotifyWebApiException) {
//                        SpotifyWebApiException spotifyWebApiException = (SpotifyWebApiException) e;
//                        System.err.println("Error: " + spotifyWebApiException.getResponse().getStatusText());
//                    } else {
//                        System.err.println("Error: " + e.getMessage());
//                    }
//                    return null;
//                })
//                .join();
//    }
//}
