package com.example.ht8_2.presentation.heroes

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ht8_2.R
import com.example.ht8_2.databinding.HeroesListItemBinding
import com.example.ht8_2.repository.SuperHero
import com.squareup.picasso.Picasso

class HeroesAdapter(private val context: Context)  : ListAdapter<SuperHero, HeroesVewHolder>(DiffCallback()) {

    var onItemClickListener: ((SuperHero) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesVewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = HeroesListItemBinding.inflate(layoutInflater, parent, false)
        return HeroesVewHolder(binding, context)
    }

    override fun onBindViewHolder(holder: HeroesVewHolder, position: Int) {
        val heroItem = getItem(position)
        holder.bind(heroItem)
        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(heroItem)
        }
    }
}

class HeroesVewHolder(private val binding: HeroesListItemBinding, private val context: Context) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: SuperHero) {
        binding.heroItemName.text = item.name
        binding.heroItemRealName.text = item.biography.fullName
        binding.maleFemale.text = item.appearance.gender
        if(item.biography.alignment == "good")
            binding.goodOrBad.setImageResource(R.drawable.good)
        else
            binding.goodOrBad.setImageResource(R.drawable.bad)
        Picasso.get()
            .load(item.images.sm)
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_foreground)
            .into(binding.heroItemImage)
    }
}

private class DiffCallback : DiffUtil.ItemCallback<SuperHero>() {
    override fun areItemsTheSame(oldItem: SuperHero, newItem: SuperHero): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: SuperHero, newItem: SuperHero): Boolean =
        oldItem.name == newItem.name
}