package com.example.ht8_2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ht8_2.databinding.FragmentHeroesListBinding
import com.example.ht8_2.cicerone.App
import com.example.ht8_2.cicerone.Screens
import com.example.ht8_2.presentation.heroes.HeroesAdapter
import com.example.ht8_2.viewModel.HeroesViewModel
import com.github.terrakok.cicerone.Router

class HeroesListFragment : Fragment() {

    lateinit var binding: FragmentHeroesListBinding
    private lateinit var adapter: HeroesAdapter
    private lateinit var vm: HeroesViewModel
    private lateinit var router: Router

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHeroesListBinding.inflate(layoutInflater)

        adapter = HeroesAdapter(requireContext())
        binding.rvHeroesList.adapter = adapter
        binding.rvHeroesList.layoutManager = LinearLayoutManager(context)
        binding.infoButton.setOnClickListener {
            router.navigateTo(Screens.info())
        }

        vm = ViewModelProvider(requireActivity())[HeroesViewModel::class.java]
        router = App.INSTANCE.router
        context?.let { vm.onInitView(it) }
        vm.heroesList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        adapter.onItemClickListener = {
            router.navigateTo(Screens.hero(it))
            vm.onClick(it)
        }

        return binding.root
    }


}