package tj.humo.currencyconvertor.ui.exchangers

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import tj.humo.currencyconvertor.data.models.ExchangerItem
import tj.humo.currencyconvertor.databinding.FragmentExchangersBinding
import tj.humo.currencyconvertor.ui.exchangers.adapter.ExchangersAdapter

class ExchangersFragment : Fragment() {

    private var _binding: FragmentExchangersBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ExchangerViewModel by viewModels()

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

        viewModel.uiState.observe(viewLifecycleOwner) {
            setLoading(it.isLoading)
            setError(it.errorMessage)
            setupRecyclerView(it.dataSet)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setLoading(isLoading: Boolean) {
        binding.progressBar.isVisible = isLoading
    }

    private fun setError(message: String?) {
        binding.errorPanel.isVisible = !message.isNullOrEmpty()
        binding.textViewErrorMessage.text = message
        binding.reloadButton.setOnClickListener {
            viewModel.reload()
        }
    }

    private fun setupRecyclerView(dataSet: List<ExchangerItem>) {
        binding.recyclerViewRates.isVisible = dataSet.isNotEmpty()
        binding.recyclerViewRates.layoutManager = LinearLayoutManager(binding.root.context)
        binding.recyclerViewRates.adapter = ExchangersAdapter(dataSet) {
            val action =
                ExchangersFragmentDirections.actionNavExchangersToNavConverter(
                    title = it.bankName
                )
            findNavController().navigate(action)
        }
    }
}
