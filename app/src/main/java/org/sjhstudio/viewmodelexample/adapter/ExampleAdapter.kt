package org.sjhstudio.viewmodelexample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sjhstudio.viewmodelexample.R
import org.sjhstudio.viewmodelexample.database.Example
import org.sjhstudio.viewmodelexample.databinding.ItemExampleBinding

interface ExampleCallback {
    fun delete(item: Example)
}

class ExampleAdapter: RecyclerView.Adapter<ExampleAdapter.ViewHolder>() {

    var items = arrayListOf<Example>()
    private var callback: ExampleCallback? = null

    fun setCallback(callback: ExampleCallback) {
        this.callback = callback
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val binding = ItemExampleBinding.bind(itemView)

        init {
            binding.deleteBtn.setOnClickListener {
                callback?.delete(items[adapterPosition])
            }
        }

        fun setBind(example: Example) {
            binding.example = example
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_example, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExampleAdapter.ViewHolder, position: Int) {
        holder.setBind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}