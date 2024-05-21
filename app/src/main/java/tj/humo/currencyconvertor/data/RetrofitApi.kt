package tj.humo.currencyconvertor.data

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tj.humo.currencyconvertor.data.api.CurrencyRateApiServices
import tj.humo.currencyconvertor.data.models.ExchangerItem
import tj.humo.currencyconvertor.data.models.NbtRateItem

object RetrofitApi {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://appsmile.ru/api/academy/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val currencyRateApiServices = retrofit.create(CurrencyRateApiServices::class.java)

    fun getNbtRates(): Call<List<NbtRateItem>> = currencyRateApiServices.getNbtRates()

    fun getExchangersRate(): Call<List<ExchangerItem>> = currencyRateApiServices.getExchangersRate()
}