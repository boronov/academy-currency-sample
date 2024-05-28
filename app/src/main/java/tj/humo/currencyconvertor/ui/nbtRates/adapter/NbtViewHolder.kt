package tj.humo.currencyconvertor.ui.nbtRates.adapter

import androidx.recyclerview.widget.RecyclerView
import tj.humo.currencyconvertor.data.remote.models.NbtRateItem
import tj.humo.currencyconvertor.databinding.ItemNbtRateBinding
import tj.humo.currencyconvertor.ui.loadRoundedImageWithCache
import tj.humo.currencyconvertor.ui.toFormattedMoney

class NbtViewHolder(private val binding: ItemNbtRateBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: NbtRateItem, listener: (NbtRateItem) -> Unit) {
        with(binding) {

            root.setOnClickListener {
                listener(item)
            }

            imageViewFlag.loadRoundedImageWithCache(item.flag)

            textViewValue.text = (item.value / item.nominal).toFormattedMoney()

            textViewCurrencyName.text = item.name
            textViewCurrencyFullName.text = item.fullName
        }
    }
}