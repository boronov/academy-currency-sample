package tj.humo.currencyconvertor.domain

interface DataMapper<Data, Domain> {
    fun map(from: Data): Domain
}