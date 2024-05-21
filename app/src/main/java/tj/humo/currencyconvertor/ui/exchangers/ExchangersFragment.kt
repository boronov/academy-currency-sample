package tj.humo.currencyconvertor.ui.exchangers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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
        setLoading(viewModel.isLoading)
        setError(viewModel.errorMessage)

        if (viewModel.dataSet.isEmpty()) {
            viewModel.loadNbtRates(
                onError = ::setError,
                onLoading = ::setLoading,
                onSuccess = ::setupRecyclerView
            )
        } else {
            setupRecyclerView(viewModel.dataSet)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setLoading(isLoading: Boolean) {
        binding.recyclerViewRates.isVisible = !isLoading
        binding.errorPanel.isVisible = !isLoading
    }

    private fun setError(message: String?) {
        binding.recyclerViewRates.isVisible = !message.isNullOrEmpty()
        binding.errorPanel.isVisible = message.isNullOrEmpty()
        binding.textViewErrorMessage.text = message
    }

    private fun setupRecyclerView(dataSet: List<ExchangerItem>) {
        binding.recyclerViewRates.isVisible = true
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
