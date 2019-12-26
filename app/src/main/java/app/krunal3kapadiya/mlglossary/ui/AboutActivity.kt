package app.krunal3kapadiya.mlglossary.ui

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import androidx.core.content.ContextCompat
import app.krunal3kapadiya.mlglossary.R
import app.krunal3kapadiya.mlglossary.ui.listing.BaseActivity
import kotlinx.android.synthetic.main.activity_about.*


class AboutActivity : BaseActivity() {
    companion object {
        fun launch(context: Context) {
            context.startActivity(Intent(context, AboutActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        rating_bar.setOnClickListener {
            launchIntentInPlayStore()
        }

        rating_bar.setOnRatingBarChangeListener { _, _, _ ->
            launchIntentInPlayStore()
        }

        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> {
                github_link.text =
                    Html.fromHtml(getString(R.string.github_link), Html.FROM_HTML_MODE_LEGACY)
            }
            else -> {
                github_link.text = Html.fromHtml(getString(R.string.github_link))
            }
        }
        github_link.movementMethod = LinkMovementMethod.getInstance()
        github_link.setLinkTextColor(ContextCompat.getColor(this, R.color.colorAccent))
    }

    fun launchIntentInPlayStore() {
        try {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=$packageName")
                )
            )
        } catch (ex: ActivityNotFoundException) {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=$packageName")
                )
            )
        }

    }
}