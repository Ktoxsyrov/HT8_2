package com.example.ht8_2.cicerone

import com.example.ht8_2.repository.SuperHero
import com.example.ht8_2.fragments.HeroDetailsFragment
import com.example.ht8_2.fragments.HeroesListFragment
import com.example.ht8_2.fragments.InfoFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {

    fun heroes() = FragmentScreen {
        HeroesListFragment()
    }

    fun hero(it: SuperHero) = FragmentScreen {
        HeroDetailsFragment()
    }

    fun info() = FragmentScreen {
        InfoFragment()
    }
}