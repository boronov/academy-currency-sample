package tj.humo.currencyconvertor.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tj.humo.currencyconvertor.R
import tj.humo.currencyconvertor.databinding.ActivityMainBinding
import tj.humo.currencyconvertor.ui.converter.ConverterFragment
import tj.humo.currencyconvertor.ui.nbtRates.NbtFragment

class MainActivity : AppCompatActivity() {
    private lateinit var converterFragment: ConverterFragment
    private lateinit var nbtFragment: NbtFragment

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        converterFragment = ConverterFragment()
        nbtFragment = NbtFragment()

        supportFragmentManager
            .beginTransaction()

            .add(R.id.container, nbtFragment, "nbtFragment")
            .add(R.id.container, converterFragment, "converterFragment")
            .commit()


        /*binding.buttonConverter.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .show(converterFragment)
                .commit()
        }

        binding.buttonNbt.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .show(nbtFragment)
                .commit()
        }*/


        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_exchangers -> {
                    supportFragmentManager
                        .beginTransaction()
                        .hide(nbtFragment)
                        .show(converterFragment)
                        .commit()
                }

                R.id.menu_nbt -> {
                    supportFragmentManager
                        .beginTransaction()
                        .hide(converterFragment)
                        .show(nbtFragment)
                        .commit()
                }
            }


            true
        }
    }


    data class User(
        val name: String,
        val age: Int,
    )
}