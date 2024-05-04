package tj.humo.currencyconvertor.data.api

import retrofit2.Call
import retrofit2.http.GET
import tj.humo.currencyconvertor.data.models.NbtRateItem

interface CurrencyRateApiServices {

    @GET("nbt_rates")
    fun getNbtRates(): Call<List<NbtRateItem>>
}