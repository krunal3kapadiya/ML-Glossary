package app.krunal3kapadiya.mlglossary.ui

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import app.krunal3kapadiya.mlglossary.BuildConfig
import app.krunal3kapadiya.mlglossary.R
import app.krunal3kapadiya.mlglossary.ui.listing.MainActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            MainActivity.launch(this)
            finish()
        }, 3000)

        txt_version.text = getString(R.string.version).plus(BuildConfig.VERSION_NAME)
    }
}