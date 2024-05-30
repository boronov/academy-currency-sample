package tj.humo.currencyconvertor.data.mapper

import tj.humo.currencyconvertor.data.local.entity.NbtRateEntity
import tj.humo.currencyconvertor.data.remote.models.NbtRateItem
import tj.humo.currencyconvertor.domain.DataMapper

class NbtRateItemMapper : DataMapper<NbtRateItem, NbtRateEntity> {
    override fun map(from: NbtRateItem): NbtRateEntity {
        return NbtRateEntity(
            name = from.name,
            fullName = from.fullName,
            nominal = from.nominal,
            flag = from.flag,
            value = from.value
        )
    }
}