package work.beltran.discogsbrowser.api

import kotlinx.coroutines.runBlocking


fun main(args: Array<String>) = runBlocking {
//    val response = provideService().getCollectionItemsByFolder("mike513", "0",1, 2)
    val response = provideService().getRelease("249504")
    print(response.await().body())
}

