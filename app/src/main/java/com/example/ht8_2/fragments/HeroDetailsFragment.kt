package com.example.ht8_2.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ht8_2.R
import com.example.ht8_2.databinding.FragmentHeroBinding
import com.example.ht8_2.viewModel.HeroesViewModel
import com.squareup.picasso.Picasso

class HeroDetailsFragment : Fragment() {

    lateinit var binding: FragmentHeroBinding
    private lateinit var vm: HeroesViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHeroBinding.inflate(layoutInflater)
        vm = ViewModelProvider(requireActivity())[HeroesViewModel::class.java]
        val hero = vm.clickedHero.value

        Picasso.get()
            .load(hero!!.images.md)
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_foreground)
            .into(binding.heroDetailsImage)
        binding.detailsName.text = hero.name
        binding.detailsPlaceOfBirth.text = "From " + hero.biography.placeOfBirth
        binding.detailsHeight.text = "Height: " + hero.appearance.height[1]
        binding.detailsWeight.text = "Weight: " + hero.appearance.weight[1]
        binding.detailsFirstAppearance.text = "First Appearance: " + hero.biography.firstAppearance
        binding.detailsPublisher.text = hero.biography.publisher
        return binding.root
    }


}