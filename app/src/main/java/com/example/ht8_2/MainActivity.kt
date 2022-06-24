package com.example.ht8_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ht8_2.databinding.ActivityMainBinding
import com.example.ht8_2.cicerone.App
import com.example.ht8_2.viewModel.HeroesViewModel
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var cicerone: Cicerone<Router>
    private lateinit var vm: ViewModel
    lateinit var appNavigator: AppNavigator
    private lateinit var fm: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.navigationBarColor = resources.getColor(R.color.black)
        fm = supportFragmentManager
        vm = ViewModelProvider(this)[HeroesViewModel::class.java]

        cicerone = Cicerone.create(App.INSTANCE.router)
        appNavigator = AppNavigator(this, R.id.main_container, fm, fm.fragmentFactory)
    }

    override fun onResume() {
        super.onResume()
        App.INSTANCE.navigatorHolder.setNavigator(appNavigator)
    }

    override fun onPause() {
        super.onPause()
        App.INSTANCE.navigatorHolder.removeNavigator()
    }
}