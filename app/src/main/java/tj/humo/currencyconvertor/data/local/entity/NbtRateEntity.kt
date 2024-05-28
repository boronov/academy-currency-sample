package tj.humo.currencyconvertor.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "nbt_rate")
data class NbtRateEntity(
    @PrimaryKey @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "nominal") val nominal: Double,
    @ColumnInfo(name = "full_name") val fullName: String,
    @ColumnInfo(name = "flag") val flag: String,
    @ColumnInfo(name = "value") val value: Double
)