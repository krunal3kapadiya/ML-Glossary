package app.krunal3kapadiya.mlglossary.ui.listing

import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import app.krunal3kapadiya.mlglossary.MLApplication
import app.krunal3kapadiya.mlglossary.R
import app.krunal3kapadiya.mlglossary.data.api.Mldefinitions
import kotlinx.android.synthetic.main.row_vocab_list.view.*
import java.util.*

/**
 * displaying words list in main screen
 */
class MLDefinitionsAdapter(private val definitions: ArrayList<Mldefinitions>) :
    RecyclerView.Adapter<MLDefinitionsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private lateinit var tts: TextToSpeech
        fun bind(definitions: Mldefinitions) {
            MLApplication.appComponent.inject(this)
            itemView.row_txt_title.text = definitions.name
            itemView.row_txt_desc.text = definitions.definition
            tts = TextToSpeech(itemView.context, TextToSpeech.OnInitListener { p0 ->
                if (p0 != TextToSpeech.ERROR) {
                    tts.language = Locale.UK
                }
            })
            itemView.img_speak_notes.setOnClickListener {
                /*Toast.makeText(itemView.context, "TODO ", Toast.LENGTH_LONG).show()*/

                tts.speak(itemView.row_txt_title.text.toString(), TextToSpeech.QUEUE_FLUSH, null)

            }
            if (tts.isSpeaking) {
                itemView.img_speak_notes.setImageDrawable(
                    ContextCompat.getDrawable(
                        itemView.context,
                        R.drawable.ic_speaker_black_24dp
                    )
                )
            }

            itemView.setOnClickListener {
                // TODO Drop Down Recycler View Here
//                val detailIntent = Intent(itemView.context, DetailActivity::class.java)
//                itemView.context.startActivity(detailIntent)
            }
        }
/*
        override fun onInit(status: Int) {
            if (status == TextToSpeech.SUCCESS) {

            }
        }*/

        /*private fun speak(textToSpeech: String) {
            if (textToSpeech != null) {*//*
                val myHashAlarm = HashMap<String, String>()
                myHashAlarm[TextToSpeech.Engine.KEY_PARAM_STREAM] =
                    AudioManager.STREAM_ALARM.toString()
                myHashAlarm[TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID] = "TTS MESSAGE"*//*
                tts.speak(textToSpeech, TextToSpeech.QUEUE_FLUSH, null)
            }
        }*/

    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.row_vocab_list,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return definitions.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(definitions[position])
    }
}