package work.beltran.discogsbrowser.app.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import work.beltran.discogsbrowser.R
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import kotlinx.android.synthetic.main.activity_main.*
import work.beltran.discogsbrowser.app.collection.CollectionController


class MainActivity : AppCompatActivity() {

    private lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        router = Conductor.attachRouter(this, controller_container, savedInstanceState)
        if (!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(CollectionController()))
        }
    }

}
