package com.example.ht8_2.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ht8_2.repository.HeroRepositoryImp
import com.example.ht8_2.repository.HeroRepositoryImp.Companion.SHARED_PREFERENCES
import com.example.ht8_2.repository.SuperHero
import kotlinx.coroutines.launch

class HeroesViewModel : ViewModel() {

    val heroesList = MutableLiveData<List<SuperHero>>()
    val clickedHero = MutableLiveData<SuperHero>()

    fun onInitView(context: Context) {
        val repository = HeroRepositoryImp(context)
        val sharedPrefs = context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)

        viewModelScope.launch {
            if (sharedPrefs.contains(SHARED_PREFERENCES)) {
                heroesList.postValue(repository.getHeroes())
            } else {
                heroesList.postValue(repository.getHeroesFromApi())
                heroesList.value?.let { repository.addHeroes(it) }
            }
        }
    }

    fun onClick(hero: SuperHero) {
        clickedHero.postValue(hero)
    }
}