package tj.humo.currencyconvertor.domain.repository

import androidx.lifecycle.LiveData
import tj.humo.currencyconvertor.domain.model.NbtRate

interface RatesRepository {
    fun getNbtRate(): LiveData<List<NbtRate>>

}