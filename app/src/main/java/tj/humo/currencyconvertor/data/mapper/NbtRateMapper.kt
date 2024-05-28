package tj.humo.currencyconvertor.data.mapper

import tj.humo.currencyconvertor.data.local.entity.NbtRateEntity
import tj.humo.currencyconvertor.domain.DataMapper
import tj.humo.currencyconvertor.domain.model.NbtRate

class NbtRateMapper : DataMapper<NbtRateEntity, NbtRate> {
    override fun map(from: NbtRateEntity): NbtRate {
        return NbtRate(
            name = from.name,
            fullName = from.fullName,
            nominal = from.nominal,
            flag = from.flag,
            value = from.value
        )
    }
}