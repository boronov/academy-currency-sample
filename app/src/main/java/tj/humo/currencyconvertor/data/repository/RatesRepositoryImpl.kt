package tj.humo.currencyconvertor.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tj.humo.currencyconvertor.data.local.dao.NbtDao
import tj.humo.currencyconvertor.data.mapper.NbtRateItemMapper
import tj.humo.currencyconvertor.data.mapper.NbtRateMapper
import tj.humo.currencyconvertor.data.remote.api.CurrencyRateApiServices
import tj.humo.currencyconvertor.data.remote.models.NbtRateItem
import tj.humo.currencyconvertor.domain.model.NbtRate
import tj.humo.currencyconvertor.domain.repository.RatesRepository

class RatesRepositoryImpl(
    private val apiService: CurrencyRateApiServices,
    private val nbtDao: NbtDao,
    private val nbtRateMapper: NbtRateMapper,
    private val nbtRateItemMapper: NbtRateItemMapper
) : RatesRepository {
    override fun getNbtRate(): LiveData<List<NbtRate>> {
        updateDataFromRemote()
        return nbtDao.getNBTRates().map {
            it.map { item -> nbtRateMapper.map(item) }
        }
    }

    private fun updateDataFromRemote() {
        apiService.getNbtRates().enqueue(object : Callback<List<NbtRateItem>> {
            override fun onResponse(p0: Call<List<NbtRateItem>>, p1: Response<List<NbtRateItem>>) {
                if (p1.isSuccessful) {
                    val list = p1.body() ?: emptyList()
                    nbtDao.updateNBTRates(list.map { item -> nbtRateItemMapper.map(item) })
                }
            }

            override fun onFailure(p0: Call<List<NbtRateItem>>, p1: Throwable) {
                // TODO
            }
        })
    }
}