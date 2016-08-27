## News - 27 August 2016

Some of the features of the project are currently broken and the app has been removed from the market.

I am currenly working on a major refactor with the following changes:

- api, business logic, ui and currency converter in separated modules.
- Major refactor of the business logic.
- Caching using a Realm database.
- Major refactor of ui classes with better MVP pattern.
- Removed Data Binding in favor of ButterKnife.
- Removed Fragments in favor of custom views.

Follow the refactors in the `refactor` branch.

# My Vinyl Discogs Browser for Android

[![Build Status](https://travis-ci.org/miquelbeltran/android-discogsbrowser.svg?branch=master)](https://travis-ci.org/miquelbeltran/android-discogsbrowser)
[![codecov](https://codecov.io/gh/miquelbeltran/android-discogsbrowser/branch/master/graph/badge.svg)](https://codecov.io/gh/miquelbeltran/android-discogsbrowser)

![logo](https://raw.githubusercontent.com/miquelbeltran/android-discogsbrowser/master/art/icon2.png)

Source code of the Android app, using Retrofit, RxJava, Dagger and more

## App Features

- Browse your Discogs collection.
- Check your wishlist (wantlist).
- Search the database.
- Get market prices.

## Code Features

- Clean app architecture: UI and API separated.
- Use of RecyclerView.
- Use of Data Binding.
- Retrofit + GSON + RxJava.
- Picasso for image loading.
- Robolectric + Mockito for Unit Testing. 
- Beatiful material design.

Public Trello board: https://trello.com/b/cpMFH9K8

## Screenshots


![Collection](https://raw.githubusercontent.com/miquelbeltran/android-discogsbrowser/master/art/screenshots/collection_640.png)

![Wantlist](https://raw.githubusercontent.com/miquelbeltran/android-discogsbrowser/master/art/screenshots/wantlist_640.png)

![Search](https://raw.githubusercontent.com/miquelbeltran/android-discogsbrowser/master/art/screenshots/search_640.png)

![Wantlist gif](https://raw.githubusercontent.com/miquelbeltran/android-discogsbrowser/master/art/screenshots/wantlist.gif)
