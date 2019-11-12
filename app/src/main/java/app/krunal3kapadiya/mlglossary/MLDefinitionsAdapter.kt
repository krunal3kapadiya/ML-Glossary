package app.krunal3kapadiya.mlglossary

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.row_vocab_list.view.*

class MLDefinitionsAdapter(private val definitions: ArrayList<Mldefinitions>) :
    RecyclerView.Adapter<MLDefinitionsAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(definitions: Mldefinitions) {
            itemView.row_txt_title.text = definitions.name
            itemView.row_txt_desc.text = definitions.definition
            itemView.img_speak_notes.setOnClickListener {
                Toast.makeText(itemView.context, "TODO ", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MLDefinitionsAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_vocab_list, parent, false))
    }

    override fun getItemCount(): Int {
        return definitions.size
    }

    override fun onBindViewHolder(holder: MLDefinitionsAdapter.ViewHolder, position: Int) {
        holder.bind(definitions[position])
    }
}