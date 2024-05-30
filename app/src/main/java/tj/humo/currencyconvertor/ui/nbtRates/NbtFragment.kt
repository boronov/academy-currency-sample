package tj.humo.currencyconvertor.ui.nbtRates

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import tj.humo.currencyconvertor.databinding.FragmentNbtRateBinding
import tj.humo.currencyconvertor.domain.model.NbtRate
import tj.humo.currencyconvertor.ui.nbtRates.adapter.NbtAdapter

class NbtFragment : Fragment() {
    private var _binding: FragmentNbtRateBinding? = null
    private val binding get() = _binding!!

    private val viewModel: NbtViewModel by viewModels(factoryProducer = { NbtViewModelProviderFactory() })

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

        viewModel.uiState.observe(viewLifecycleOwner) {
            setLoading(it.isLoading)
            setError(it.errorMessage)
            setupRecyclerView(it.dataSet)
        }
    }

    private fun setLoading(isLoading: Boolean) {
        binding.progressBar.isVisible = isLoading
    }

    private fun setError(message: String?) {
        binding.errorPanel.isVisible = !message.isNullOrEmpty()
        binding.textViewErrorMessage.text = message
        binding.reloadButton.setOnClickListener {
            ///    viewModel.reload()
        }
    }

    private fun setupRecyclerView(dataSet: List<NbtRate>) {
        binding.contentPanel.isVisible = dataSet.isNotEmpty()
        binding.recyclerViewRates.layoutManager = LinearLayoutManager(binding.root.context)
        binding.recyclerViewRates.adapter = NbtAdapter(dataSet) {
            val action = NbtFragmentDirections.actionNavNbtToNavConverter(it.fullName)
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}