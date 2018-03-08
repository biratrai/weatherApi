# WeatherApp
This is a sample application to use the WeatherApi from [Dark Sky Api](https://darksky.net/dev).

It uses the android [Instant app](https://developer.android.com/topic/instant-apps/index.html) feature.

The application has three modules.
1. weather-base - Base Feature Module that should be included in both our main app and instant app.
2. weatherapp-apk - Main app module that has other feature that is not included in the instant app or in the weather-base module.
3. weather-instantapp - Instant app module which has the link for instant app.

## Walkthrough of all the function in the project.
![weather-app](https://github.com/biratrai/weatherApi/blob/master/images/weather.gif?raw=true)
## External libraries used:

1. compile 'com.jakewharton:butterknife:8.8.1'
2. compile 'com.mcxiaoke.volley:library:1.0.19'
3. compile 'de.greenrobot:eventbus:2.4.0'
4. compile 'com.android.support:support-v4:27.0.2'
5. compile 'com.android.support:cardview-v7:27.0.2'
6. compile 'com.google.code.gson:gson:2.8.2'
7. compile 'com.github.bumptech.glide:glide:3.5.2'
8. compile 'jp.wasabeef:recyclerview-animators:2.2.1'

