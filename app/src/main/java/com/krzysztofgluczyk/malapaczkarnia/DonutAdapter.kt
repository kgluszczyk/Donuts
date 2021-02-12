package com.krzysztofgluczyk.malapaczkarnia

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.krzysztofgluczyk.malapaczkarnia.databinding.DonutItemBinding

class DonutAdapter(private var items: List<Donut> = emptyList()) : RecyclerView.Adapter<DonutViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DonutViewHolder {
        val view = DonutItemBinding.inflate(LayoutInflater.from(parent.context))
        return DonutViewHolder(view)
    }

    override fun onBindViewHolder(holder: DonutViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setData(items: List<Donut>){
        this.items = items
    }

    override fun getItemCount() = items.size
}

class DonutViewHolder(private val binding: DonutItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(donut: Donut) {
        with(binding) {
            title.text = donut.title
            subtitle.text = donut.alternativeName
            Glide.with(root.context)
                .load(donut.image)
                .centerCrop()
                .into(binding.image)
        }
    }
}