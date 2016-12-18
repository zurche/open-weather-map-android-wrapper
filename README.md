# open-weather-map-android-wrapper [![Release](https://jitpack.io/v/zurche/open-weather-map-android-wrapper.svg)](https://jitpack.io/#zurche/open-weather-map-android-wrapper/v0.1) [![Method Count](https://img.shields.io/badge/Methods and size-79 | 39 KB-e91e63.svg)](http://www.methodscount.com/?lib=com.github.zurche%3Aopen-weather-map-android-wrapper%3Av0.1) 
Non-official API wrapper of OpenWeatherMap.org for Android made with Retrofit 2.0

Add to your project build.gradle
```gradle
allprojects {
	repositories {
		...
		maven { url "https://jitpack.io" }
	}
}
```

Add to your app module build.gradle
```gradle
dependencies {
        compile 'com.github.zurche:open-weather-map-android-wrapper:v0.1'
}
```

Also remember to add the INTERNET permission in your Manifest:
```xml
<uses-permission android:name="android.permission.INTERNET"/>
```

Check a usage example of this library here: https://github.com/zurche/color-weather

The wrapper currently supports the following endpoints of OpenWeatherMap API:
* 5 Day Forecast (https://openweathermap.org/forecast5)
* Current Weather (https://openweathermap.org/current)


## OWService setup
To use the API you need a valid OpenWeatherMap token afterwards you can create your API object and start working with it:
```java
private OWService mOWService = new OWService("<YOUR TOKEN GOES HERE>");
```

Once your service object is created you can configure it with the desired language and metric units in which you need the forecast temperatures retrieved:
```java
mOWService.setLanguage(getActivity().getResources().getConfiguration().locale);
mOWService.setMetricUnits(OWSupportedUnits.METRIC);
```

## five-day forecast
To retrieve 5 Days Forecast the library populates the [ExtendedWeather](https://github.com/zurche/open-weather-map-android-wrapper/blob/master/OWApi/src/main/java/az/openweatherapi/model/gson/five_day/ExtendedWeather.java) object with the retrieved information. To set the location you need to get the weather forecast create a Coor object with the desired Lat/Lon value:
```java
Coord coordinate = new Coord();
coordinate.setLat(-31.42);
coordinate.setLon(-64.18);

mOWService.getFiveDayForecast(coordinate, new OWRequestListener<ExtendedWeather>() {
      @Override
      public void onResponse(OWResponse<ExtendedWeather> response) {
          ExtendedWeather extendedWeather = response.body();
          //Do something with the object here!
      }

      @Override
      public void onFailure(Throwable t) {
          Log.e(TAG, "Five Day Forecast request failed: " + t.getMessage());
      }
  });
```

## current-day forecast
To get the current day forecast you also need to send the Coord object with the Lat/Lon pair of the location to retrieve the weather from and the library will respond with a [CurrentWeather](https://github.com/zurche/open-weather-map-android-wrapper/blob/master/OWApi/src/main/java/az/openweatherapi/model/gson/current_day/CurrentWeather.java) object populated with the temperature information:
```java
Coord coordinate = new Coord();
coordinate.setLat(-31.42);
coordinate.setLon(-64.18);

mOWService.getCurrentDayForecast(coordinate, new OWRequestListener<CurrentWeather>() {
    @Override
    public void onResponse(OWResponse<CurrentWeather> response) {
        CurrentWeather currentWeather = response.body();
        //Do something with the object here!
    }

    @Override
    public void onFailure(Throwable t) {
        Log.e(TAG, "Current Day Forecast request failed: " + t.getMessage());
    }
});
```


License
--------

    Copyright 2016 Alejandro Zürcher

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
