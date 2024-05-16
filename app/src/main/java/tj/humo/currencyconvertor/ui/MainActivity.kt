package tj.humo.currencyconvertor.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import tj.humo.currencyconvertor.R
import tj.humo.currencyconvertor.databinding.ActivityMainBinding
import tj.humo.currencyconvertor.ui.converter.ConverterFragment
import tj.humo.currencyconvertor.ui.nbtRates.NbtFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController =
            (supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment).navController

        val topIdSet = setOf(
            R.id.nav_exchangers,
            R.id.nav_nbt
        )

        NavigationUI.setupWithNavController(
            binding.toolbar, navController, AppBarConfiguration(
                topIdSet
            )
        )

        NavigationUI.setupWithNavController(binding.bottomNavigation, navController)

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            binding.bottomNavigation.isVisible = topIdSet.contains(destination.id)
        }
    }
}