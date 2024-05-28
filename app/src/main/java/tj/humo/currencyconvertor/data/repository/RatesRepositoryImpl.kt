package tj.humo.currencyconvertor.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import tj.humo.currencyconvertor.data.local.dao.NbtDao
import tj.humo.currencyconvertor.data.mapper.NbtRateMapper
import tj.humo.currencyconvertor.data.remote.api.CurrencyRateApiServices
import tj.humo.currencyconvertor.domain.model.NbtRate
import tj.humo.currencyconvertor.domain.repository.RatesRepository

class RatesRepositoryImpl(
    private val apiService: CurrencyRateApiServices,
    private val nbtDao: NbtDao,
    private val nbtRateMapper: NbtRateMapper
) : RatesRepository {
    override fun getNbtRate(): LiveData<List<NbtRate>> {
        return nbtDao.getNBTRates().map {
            it.map { item -> nbtRateMapper.map(item) }
        }
    }
}