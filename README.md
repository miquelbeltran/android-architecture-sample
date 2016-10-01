## News - 01 October 2016

- Using AutoValue on all the API model classes.
- Using AutoValue on the RecordAdapterItem.
- Also separated the RecordAdapterItem to the API model classes.
- The state of the adapters, recyclerviews and presenters inside the custom views for Collections and Wantlist now support configuration changes (the headers will come next).
- Added auto-value-gson and auto-value-parcel to help with the work.

## News - 28 August 2016

Some of the features of the project are currently broken and the app has been removed from the market.

There has been a major refactor with the following changes:

- api, business logic, ui and currency converter in separated modules.
- Major refactor of the business logic.
- Major refactor of ui classes with better MVP pattern.
- Removed Data Binding in favor of ButterKnife.
- Removed Fragments in favor of custom views.

# My Vinyl Discogs Browser for Android

[![BuddyBuild](https://dashboard.buddybuild.com/api/statusImage?appID=5746acc88191a20100875e17&branch=master&build=latest)](https://dashboard.buddybuild.com/apps/5746acc88191a20100875e17/build/latest)

![logo](https://raw.githubusercontent.com/miquelbeltran/android-discogsbrowser/master/art/icon2.png)

Source code of the Android app, using Retrofit, RxJava, Dagger and more

## App Features

- Browse your Discogs collection.
- Check your wishlist (wantlist).
- Search the database.
- ~~Get market prices.~~ currently not working

## Code Features

- Clean app architecture in different modules.
- Dependency injection everywhere with Dagger 2.
- Retrofit + GSON + RxJava.
- Picasso for image loading.
- Robolectric + Mockito for Unit Testing.
- Beatiful material design.

## Screenshots


![Collection](https://raw.githubusercontent.com/miquelbeltran/android-discogsbrowser/master/art/screenshots/collection_640.png)

![Wantlist](https://raw.githubusercontent.com/miquelbeltran/android-discogsbrowser/master/art/screenshots/wantlist_640.png)

![Search](https://raw.githubusercontent.com/miquelbeltran/android-discogsbrowser/master/art/screenshots/search_640.png)

![Wantlist gif](https://raw.githubusercontent.com/miquelbeltran/android-discogsbrowser/master/art/screenshots/wantlist.gif)
