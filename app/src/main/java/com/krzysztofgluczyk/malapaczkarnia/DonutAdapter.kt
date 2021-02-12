package com.krzysztofgluczyk.malapaczkarnia

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.krzysztofgluczyk.malapaczkarnia.databinding.DonutItemBinding

class DonutAdapter(private var items: List<Donut> = emptyList(), private val onDonutClickListener: DonutClick) :
    RecyclerView.Adapter<DonutViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DonutViewHolder {
        val view = DonutItemBinding.inflate(LayoutInflater.from(parent.context))
        return DonutViewHolder(view, onDonutClickListener)
    }

    override fun onBindViewHolder(holder: DonutViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setData(items: List<Donut>) {
        this.items = items
    }

    override fun getItemCount() = items.size
}

class DonutViewHolder(private val binding: DonutItemBinding, private val onDonutClickListener: DonutClick) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(donut: Donut) {
        with(binding) {
            root.setOnClickListener {
                AnimationUtils.loadAnimation(root.context, android.R.anim.fade_out).run {
                    setAnimationListener(object : Animation.AnimationListener {
                        override fun onAnimationStart(animation: Animation?) {
                        }

                        override fun onAnimationEnd(animation: Animation?) {
                            root.visibility = View.GONE
                        }

                        override fun onAnimationRepeat(animation: Animation?) {
                        }

                    })
                    image.startAnimation(this)
                }
                onDonutClickListener(donut)
            }
            root.visibility = View.VISIBLE
            title.text = donut.title
            subtitle.text = donut.alternativeName
            Glide.with(root.context)
                .load(donut.image)
                .centerCrop()
                .into(binding.image)
        }
    }
}

typealias DonutClick = (Donut) -> Unit