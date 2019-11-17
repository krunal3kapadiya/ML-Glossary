package app.krunal3kapadiya.mlglossary

import android.content.Context
import android.content.Intent
import android.os.Build
import android.speech.tts.TextToSpeech
import android.support.annotation.RequiresApi
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.row_vocab_list.view.*
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList
import android.media.AudioManager
import android.speech.tts.UtteranceProgressListener
import android.util.Log


class MLDefinitionsAdapter(private val definitions: ArrayList<Mldefinitions>) :
    RecyclerView.Adapter<MLDefinitionsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        @Inject
        lateinit var context: Context
        private lateinit var tts: TextToSpeech

        fun bind(definitions: Mldefinitions) {
            MLApplication.appComponent.inject(this)
            itemView.row_txt_title.text = definitions.name
            itemView.row_txt_desc.text = definitions.definition
            tts = TextToSpeech(context, TextToSpeech.OnInitListener { p0 ->
                if (p0 != TextToSpeech.ERROR) {
                    tts.language = Locale.UK
                }
            })
            itemView.img_speak_notes.setOnClickListener {
                /*Toast.makeText(itemView.context, "TODO ", Toast.LENGTH_LONG).show()*/

                tts.speak(itemView.row_txt_title.text.toString(), TextToSpeech.QUEUE_FLUSH, null)

            }
            if(tts.isSpeaking){
                itemView.img_speak_notes.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        R.drawable.ic_speaker_black_24dp
                    )
                )
            }

            itemView.setOnClickListener {
                val detailIntent = Intent(context, DetailActivity::class.java)
                context.startActivity(detailIntent)
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

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MLDefinitionsAdapter.ViewHolder {
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

    override fun onBindViewHolder(holder: MLDefinitionsAdapter.ViewHolder, position: Int) {
        holder.bind(definitions[position])
    }
}