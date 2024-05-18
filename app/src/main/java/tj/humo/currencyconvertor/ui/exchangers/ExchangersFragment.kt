package tj.humo.currencyconvertor.ui.exchangers

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tj.humo.currencyconvertor.data.RetrofitApi
import tj.humo.currencyconvertor.data.models.ExchangerItem
import tj.humo.currencyconvertor.databinding.FragmentExchangersBinding
import tj.humo.currencyconvertor.ui.exchangers.adapter.ExchangersAdapter

class ExchangersFragment : Fragment() {

    private var _binding: FragmentExchangersBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding?.progressBar?.isVisible = false
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentExchangersBinding.inflate(layoutInflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadNbtRates()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun loadNbtRates() {
        binding.progressBar.isVisible = true
        binding.recyclerViewRates.isVisible = false
        binding.errorPanel.isVisible = false


        RetrofitApi.getExchangersRate().enqueue(object : Callback<List<ExchangerItem>> {
            override fun onResponse(
                p0: Call<List<ExchangerItem>>,
                p1: Response<List<ExchangerItem>>
            ) {
                if (this@ExchangersFragment.isAdded) {
                    binding.progressBar.isVisible = false

                    if (p1.isSuccessful) {
                        binding.recyclerViewRates.adapter =
                            ExchangersAdapter(dataSet = p1.body() ?: emptyList()) {
                                val action =
                                    ExchangersFragmentDirections.actionNavExchangersToNavConverter(
                                        title = it.bankName
                                    )
                                findNavController().navigate(action)
                            }

                        binding.recyclerViewRates.isVisible = true
                    } else {

                        binding.errorPanel.isVisible = true
                        binding.textViewErrorMessage.text = "Что-то пошло не так"
                    }
                }
            }

            override fun onFailure(p0: Call<List<ExchangerItem>>, p1: Throwable) {
                if (this@ExchangersFragment.isAdded) {
                    binding.progressBar.isVisible = false
                    binding.recyclerViewRates.isVisible = false
                    binding.errorPanel.isVisible = true
                    binding.textViewErrorMessage.text = p1.message
                }
            }
        })
    }
}