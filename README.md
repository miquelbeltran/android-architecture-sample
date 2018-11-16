# Sample Discogs Browser for Android

Sample Android App to access Discogs API.

This app performs basic REST call to obtain a public list of records, 
with the idea to test and showcase some Android architecture and coding
patterns.

## Code Features

- Kotlin.
- Coroutines.
- Dependency injection with Koin.
- Retrofit + Moshi.
- Architecture Components.
- Modularization.
- Unit Testing.
- Instrumentation Testing with Espresso.

## Cool Examples

#### Dependency Injection with Koin

https://github.com/miquelbeltran/android-discogsbrowser/blob/master/app/src/main/java/work/beltran/discogsbrowser/App.kt#L11
```kotlin
startKoin(this, listOf(
    collectionKoinModule
))
```
https://github.com/miquelbeltran/android-discogsbrowser/blob/master/collection/collection-ui/src/main/java/work/beltran/discogsbrowser/collection/CollectionFragment.kt#L20
```kotlin
private val viewModel: CollectionViewModel by viewModel()
```

#### Retrofit with Coroutines

https://github.com/miquelbeltran/android-discogsbrowser/blob/master/api/src/main/java/work/beltran/discogsbrowser/api/DiscogsService.kt#L22

```kotlin
@GET("users/{username}/collection/folders/{folder_id}/releases")
fun getCollectionItemsByFolder(
    @Path("username") username: String,
    @Path("folder_id") folderId: String,
    @Query("page") page: Int,
    @Query("per_page") perPage: Int
) : Deferred<Response<CollectionItemsByFolderResponse>>
```

#### Use Case Implementation with Coroutines and Arrow

https://github.com/miquelbeltran/android-discogsbrowser/blob/master/collection/collection-data/src/main/java/work/beltran/discogsbrowser/collection/data/GetCollectionUseCase.kt#L17

```kotlin
private suspend fun requestItems(
    folder: String,
    page: Int
): Response<CollectionItemsByFolderResponse> {
    return discogsService.getCollectionItemsByFolder(
        username = "mike513",
        folderId = folder,
        page = page,
        perPage = PAGE_SIZE
    ).await()
}
```

#### Use Case Unit Test using Mockk and Coroutines

https://github.com/miquelbeltran/android-discogsbrowser/blob/master/collection/collection-data/src/test/kotlin/work/beltran/discogsbrowser/collection/data/GetCollectionUseCaseImplTest.kt

```kotlin
every {
    service.getCollectionItemsByFolder(
        username = "mike513",
        folderId = "0",
        page = 1,
        perPage = 20
    )
} returns CompletableDeferred(
    Response.success(
        collectionItemsByFolderResponse
    )
)
```

#### ViewModel and LiveData with Coroutines (Kotlin 1.3)

https://github.com/miquelbeltran/android-discogsbrowser/blob/master/collection/collection-ui/src/main/java/work/beltran/discogsbrowser/collection/CollectionViewModel.kt

```kotlin
loadingJob = launch(dispatcher) {
    val result = collectionUseCase.getCollectionPage("0", page)
    when (result) {
        is Either.Left -> Log.e("CollectionViewModel", result.a)
        is Either.Right -> {
            pages[page] = result.b.data
            nextPage = result.b.nextPage
        }
    }
    updateCollectionScreenState()
}
```

#### ViewModel Unit Test

https://github.com/miquelbeltran/android-discogsbrowser/blob/master/collection/collection-ui/src/test/java/work/beltran/discogsbrowser/collection/CollectionViewModelTest.kt

```kotlin
val viewModel = CollectionViewModel(
    collectionUseCase = useCase,
    dispatcher = Dispatchers.Unconfined
)
```

#### Fragment Instrumentation Test with Espresso

https://github.com/miquelbeltran/android-discogsbrowser/blob/master/collection/collection-ui/src/androidTest/java/work/beltran/discogsbrowser/collection/CollectionFragmentTest.kt

## App Modules

```
- app                  (App and MainActivity)
- api                  (Retrofit Service)
- common-domain
- collection           (Feature)
  | - collection-ui    (Fragment and ViewModel)
  | - collection-data  (Use case)
```

