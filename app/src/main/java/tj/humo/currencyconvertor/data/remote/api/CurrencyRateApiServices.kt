package tj.humo.currencyconvertor.data.remote.api

import retrofit2.Call
import retrofit2.http.GET
import tj.humo.currencyconvertor.data.remote.models.ExchangerItem
import tj.humo.currencyconvertor.data.remote.models.NbtRateItem

interface CurrencyRateApiServices {

    @GET("data.json")
    fun getNbtRates(): Call<List<NbtRateItem>>

    @GET("npcr_bank_rates_data.json")
    fun getExchangersRate(): Call<List<ExchangerItem>>
}