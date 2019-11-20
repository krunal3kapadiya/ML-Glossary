package app.krunal3kapadiya.mlglossary.ui

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import app.krunal3kapadiya.mlglossary.R
import app.krunal3kapadiya.mlglossary.ui.listing.MainActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            MainActivity.launch(this)
            finish()
        }, 3000)
    }
}