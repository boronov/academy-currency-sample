package tj.humo.currencyconvertor.ui.nbtRates

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tj.humo.currencyconvertor.data.remote.RetrofitApi
import tj.humo.currencyconvertor.data.remote.models.NbtRateItem
import tj.humo.currencyconvertor.databinding.FragmentNbtRateBinding
import tj.humo.currencyconvertor.ui.nbtRates.adapter.NbtAdapter

class NbtFragment : Fragment() {

    private var _binding: FragmentNbtRateBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: NbtAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNbtRateBinding.inflate(layoutInflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupList() {
        if (::adapter.isInitialized) {
            binding.recyclerViewRates.adapter = adapter
        } else {
            loadNbtRates()
        }
    }

    private fun loadNbtRates() {
        binding.progressBar.isVisible = true
        binding.contentPanel.isVisible = false
        binding.errorPanel.isVisible = false


        RetrofitApi.getNbtRates().enqueue(object : Callback<List<NbtRateItem>> {
            override fun onResponse(p0: Call<List<NbtRateItem>>, p1: Response<List<NbtRateItem>>) {
                if (this@NbtFragment.isAdded) {
                    binding.progressBar.isVisible = false

                    if (p1.isSuccessful) {
                        adapter = NbtAdapter(dataSet = p1.body() ?: emptyList()) {
                            val action =
                                NbtFragmentDirections.actionNavNbtToNavConverter(
                                    title = it.name
                                )
                            findNavController().navigate(action)
                        }

                        binding.recyclerViewRates.adapter = adapter

                        binding.contentPanel.isVisible = true
                    } else {

                        binding.errorPanel.isVisible = true
                        binding.textViewErrorMessage.text = "Что-то пошло не так"
                    }
                }
            }

            override fun onFailure(p0: Call<List<NbtRateItem>>, p1: Throwable) {
                if (this@NbtFragment.isAdded) {
                    binding.progressBar.isVisible = false
                    binding.contentPanel.isVisible = false
                    binding.errorPanel.isVisible = true
                    binding.textViewErrorMessage.text = p1.message
                }
            }
        })
    }
}