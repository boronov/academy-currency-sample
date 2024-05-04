package tj.humo.currencyconvertor.ui.nbtRates

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tj.humo.currencyconvertor.data.RetrofitApi
import tj.humo.currencyconvertor.data.models.NbtRateItem
import tj.humo.currencyconvertor.databinding.FragmentNbtRatesBinding
import tj.humo.currencyconvertor.ui.nbtRates.adapter.NbtAdapter

class NbtFragment : Fragment() {

    private var _binding: FragmentNbtRatesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNbtRatesBinding.inflate(layoutInflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadNbtRates()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        Log.d("TAG_TEST", "onDestroyView: ")
        _binding = null
    }

    private fun loadNbtRates() {
        binding.progressBar.isVisible = true
        binding.contentPanel.isVisible = false
        binding.errorPanel.isVisible = false


        RetrofitApi.getNbtRates().enqueue(object : Callback<List<NbtRateItem>> {
            override fun onResponse(p0: Call<List<NbtRateItem>>, p1: Response<List<NbtRateItem>>) {
                binding.progressBar.isVisible = false

                if (p1.isSuccessful) {
                    binding.recyclerViewContact.adapter =
                        NbtAdapter(dataSet = p1.body() ?: emptyList()) {

                            // TODO
                            Toast.makeText(requireContext(), it.name, Toast.LENGTH_SHORT).show()
                        }

                    binding.contentPanel.isVisible = true
                } else {

                    binding.errorPanel.isVisible = true
                    binding.textViewErrorMessage.text = "Что-то пошло не так"
                }
            }

            override fun onFailure(p0: Call<List<NbtRateItem>>, p1: Throwable) {
                binding.progressBar.isVisible = false
                binding.contentPanel.isVisible = false
                binding.errorPanel.isVisible = true
                binding.textViewErrorMessage.text = p1.message
            }

        })
    }
}