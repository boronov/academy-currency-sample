package tj.humo.currencyconvertor.ui.nbtRates

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import tj.humo.currencyconvertor.data.mapper.NbtRateItemMapper
import tj.humo.currencyconvertor.data.mapper.NbtRateMapper
import tj.humo.currencyconvertor.data.repository.RatesRepositoryImpl
import tj.humo.currencyconvertor.domain.repository.RatesRepository
import tj.humo.currencyconvertor.ui.App

class NbtViewModelProviderFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        val repository: RatesRepository = RatesRepositoryImpl(
            apiService = App.currencyRateApiServices,
            nbtDao = App.nbtDao,
            nbtRateMapper = NbtRateMapper(),
            nbtRateItemMapper = NbtRateItemMapper()
        )

        return NbtViewModel(repository) as T
    }
}