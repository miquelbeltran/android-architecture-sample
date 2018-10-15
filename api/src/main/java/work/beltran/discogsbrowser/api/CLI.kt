package work.beltran.discogsbrowser.api

fun main(args: Array<String>) {
    val response = provideService().getCollectionItemsByFolder("mike513", "0",1, 20).execute()

    print(response.body())
}

