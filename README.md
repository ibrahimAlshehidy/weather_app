Weather App Demo

this app using the open weather API https://www.weatherapi.com to show current weather for the
chosen city and show the weather for the next two days.

## How to run?

This project needs Android Studio with Android Gradle plugin 7.1.2

It's recommended to open it using <B>Android Studio Arctic Fox</B>.

To choose city just you need to tap on search icon and start typing to see recommendations based on
what you typed, once you choose city it will show you the weather information for today and for the
next two days.

## Architecture

Clean architecture based on MVVM (Model-View-ViewModel)

## Built With 🛠

* [Kotlin](https://kotlinlang.org/) - official programming language for Android development.
* [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - for asynchronous or
  non-blocking programming.
* [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) -
  Part of Jetpack it's a set of libraries that help you design robust, testable, and maintainable
  apps.
    - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - an
      observable data holder class.
    - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - store and
      manage UI-related data in a lifecycle conscious way.
* [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - Dependency
  Injection Framework.
* [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android.
* [Material Design](https://material.io/design/guidelines-overview) are interactive building blocks
  for creating a friendly user interface.
* [Glide](https://github.com/bumptech/glide) An image loading library.
