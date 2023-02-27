<!--
This README describes the package. If you publish this package to pub.dev,
this README's contents appear on the landing page for your package.

For information about how to write a good package README, see the guide for
[writing package pages](https://dart.dev/guides/libraries/writing-package-pages).

For general information about developing packages, see the Dart guide for
[creating packages](https://dart.dev/guides/libraries/create-library-packages)
and the Flutter guide for
[developing packages and plugins](https://flutter.dev/developing-packages).
-->

## Features

This is a personal flutter plugin written to work with Stytch's android sdk written in Kotlin.

It is very Incomplete and discontinued by me because the Stytch Android Sdk doesn't offer the intended behaviour I'm going for.

Only the Google Sign In Scheme on Android for Stytch has been done here.

only version 0.9.0 of Styth's Android SDK was implemented.
consider bumping this up if you wish to improve this.

Feel free to contact me if you want to extend this.


## Test It Out

So Ideally, You have finished setting up every thing from the stytch dashboard
and from GCP.

### Setup
app/build.gradle setup
1. compileSdkVersion 33
2. minSdkVersion 23
3.  manifestPlaceholders += [
            'stytchOAuthRedirectScheme': 'app',
            'stytchOAuthRedirectHost': 'oauth'
        ]
    The += ensures you're adding to the manifestPlaceHolders list and not overwriting it.

### Import
Import the repo is pubspec.yaml like so
```
stytch_auth:
    git:
      url: https://github.com/adolesamuel/stytch_auth.git
      ref: main
```

### Configure
Configure the StytchAuthFlutter Package with the public Token from your Stytch dashboard in main() after WidgetsBinding.ensureInitialized().
```
WidgetsBinding.ensureInitialized();

StytchAuthFlutter stytchAuthFlutter = StytchAuthFlutter.instance;
stytchAuthFlutter.configure(
      publicToken: '<publicToken>');
```

### SignIn example
using the redirect urls from Stytch dashboard make request to do google Sign in a button like.
```
ElevatedButton(
onPressed: () {
                final stytch = StytchAuthFlutter.instance;
                stytch.loginWithGoogle(loginRedirectUrl:<loginRedirectUrl> , signupRedirectUrl: <signUpRedirectUrl>);
              },), 
```

Feel free to submit a PR to handle the response object after sign-in.


## Additional information

This has been abandoned. I only Implemented Signin with Google on Android.
