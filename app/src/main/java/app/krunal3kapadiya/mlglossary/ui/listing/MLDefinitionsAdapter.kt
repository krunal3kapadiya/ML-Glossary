package app.krunal3kapadiya.mlglossary.ui.listing

import android.content.Context
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import app.krunal3kapadiya.mlglossary.MLApplication
import app.krunal3kapadiya.mlglossary.R
import app.krunal3kapadiya.mlglossary.data.api.Mldefinitions
import app.krunal3kapadiya.mlglossary.ui.listing.MLDefinitionsAdapter.*
import kotlinx.android.synthetic.main.row_vocab_list.view.*
import java.util.*

/**
 * displaying words list in main screen
 */
class MLDefinitionsAdapter(
    context: Context,
    private val definitions: ArrayList<Mldefinitions>
) :
    RecyclerView.Adapter<ViewHolder>(), OnClickListener {

    private val clickListener: OnClickListener = context as OnClickListener

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private lateinit var tts: TextToSpeech
        fun bind(definitions: Mldefinitions) {
            itemView.row_txt_title.text = definitions.name
            itemView.row_txt_desc.text = definitions.definition
            tts = TextToSpeech(itemView.context, TextToSpeech.OnInitListener { p0 ->
                if (p0 != TextToSpeech.ERROR) {
                    tts.language = Locale.UK
                }
            })
            itemView.img_speak_notes.setOnClickListener {
                tts.speak(itemView.row_txt_title.text.toString(), TextToSpeech.QUEUE_FLUSH, null)

            }
            if (tts.isSpeaking) itemView.img_speak_notes.setImageDrawable(
                ContextCompat.getDrawable(
                    itemView.context,
                    R.drawable.ic_speaker_black_24dp
                )
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.row_vocab_list,
            parent,
            false
        )
    )

    override fun getItemCount(): Int = definitions.size

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.itemView.setOnClickListener {
            clickListener.onClickListener(holder.adapterPosition)
        }
        holder.bind(definitions[position])
    }
}