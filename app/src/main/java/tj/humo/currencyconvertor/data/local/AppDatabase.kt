package tj.humo.currencyconvertor.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import tj.humo.currencyconvertor.data.local.dao.NbtDao
import tj.humo.currencyconvertor.data.local.entity.NbtRateEntity

@Database(entities = [NbtRateEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun nbtDao(): NbtDao
}