# WeatherApp
This is a sample application to use the WeatherApi from [Dark Sky Api](https://darksky.net/dev).

It uses the android [Instant app](https://developer.android.com/topic/instant-apps/index.html) feature.

The application has three modules.
#### weather-base
  - Base Feature Module that should be included in both our main app and instant app.
#### weatherapp-apk
- Main app module that has other feature that is not included in the instant app or in the weather-base module.
#### weather-instantapp
- Instant app module which has the link for instant app.

## Walkthrough of all the function in the project.
![weather-app](https://github.com/biratrai/weatherApi/blob/master/images/weather.gif?raw=true)
## External libraries used:

1. compile 'com.jakewharton:butterknife:8.8.1'
2. compile 'jp.wasabeef:recyclerview-animators:2.2.1'
3. compile 'de.greenrobot:eventbus:2.4.0'
4. compile 'com.android.support:support-v4:27.0.2'
5. compile 'com.android.support:cardview-v7:27.0.2'
6. compile 'com.google.code.gson:gson:2.8.2'
7. compile 'com.github.bumptech.glide:glide:3.5.2'
8. compile 'jp.wasabeef:recyclerview-animators:2.2.1'
9. compile 'com.squareup.retrofit2:retrofit:2.3.0'
10. compile 'com.squareup.retrofit2:converter-gson:2.3.0'

