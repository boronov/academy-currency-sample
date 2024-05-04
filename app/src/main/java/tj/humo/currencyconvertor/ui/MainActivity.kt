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
            .replace(R.id.container, nbtFragment)
            .commit()


        binding.buttonConverter.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, converterFragment)
                .commit()
        }

        binding.buttonNbt.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, nbtFragment)
                .commit()
        }
    }
}