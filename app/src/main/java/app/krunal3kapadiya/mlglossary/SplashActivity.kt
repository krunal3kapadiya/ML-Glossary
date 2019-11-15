package app.krunal3kapadiya.mlglossary

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var context: Context


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        MLApplication.appComponent.inject(this)
        Handler().postDelayed({
            MainActivity.launch(context)
            finish()
        }, 3000)
    }
}