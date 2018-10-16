package work.beltran.discogsbrowser

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.koin.android.ext.android.startKoin
import work.beltran.discogsbrowser.collection.collectionKoinModule

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startKoin(this, listOf(
            collectionKoinModule
        ))
        setContentView(R.layout.activity_main)
    }
}
