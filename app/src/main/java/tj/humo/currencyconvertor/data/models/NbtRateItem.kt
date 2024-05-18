package tj.humo.currencyconvertor.data.models

import com.google.gson.annotations.SerializedName

data class NbtRateItem(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("nominal")
    val nominal: Double,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("flag")
    val flag: String,
    @SerializedName("value")
    val value: Double
)