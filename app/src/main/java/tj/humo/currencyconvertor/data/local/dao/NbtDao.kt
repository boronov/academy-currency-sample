package tj.humo.currencyconvertor.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import tj.humo.currencyconvertor.data.local.entity.NbtRateEntity

@Dao
interface NbtDao {
    @Query("SELECT * FROM nbt_rate")
    fun getNBTRates(): LiveData<List<NbtRateEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateNBTRates(rates: List<NbtRateEntity>)
}