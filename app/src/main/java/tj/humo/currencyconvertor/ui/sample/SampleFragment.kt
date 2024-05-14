package tj.humo.currencyconvertor.ui.sample

import android.os.Bundle
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import tj.humo.currencyconvertor.R

class SampleFragment : Fragment(R.layout.fragment_converter) {

    private val args: SampleFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        bundleOf()
        Toast.makeText(
            requireContext(),
            args.currencyName,
            Toast.LENGTH_SHORT
        ).show()


        args.toolbarTitleNew

    }
}