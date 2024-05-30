package tj.humo.currencyconvertor.ui

import android.app.Application
import androidx.room.Room
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tj.humo.currencyconvertor.data.local.AppDatabase
import tj.humo.currencyconvertor.data.local.dao.NbtDao
import tj.humo.currencyconvertor.data.remote.api.CurrencyRateApiServices

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://appsmile.ru/api/academy/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        currencyRateApiServices = retrofit.create(CurrencyRateApiServices::class.java)

        val db = Room.databaseBuilder(
            this,
            AppDatabase::class.java, "currency-database"
        )
            .allowMainThreadQueries()
            .build()

        nbtDao = db.nbtDao()
    }

    companion object {
        lateinit var instance: App

        lateinit var currencyRateApiServices: CurrencyRateApiServices
        lateinit var nbtDao: NbtDao
    }
}