package tj.humo.currencyconvertor.domain.model

data class NbtRate(
    val name: String,
    val nominal: Double,
    val fullName: String,
    val flag: String,
    val value: Double
)