package tj.humo.currencyconvertor.ui.exchangers.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import tj.humo.currencyconvertor.R
import tj.humo.currencyconvertor.data.models.ExchangerItem
import tj.humo.currencyconvertor.data.models.NbtRateItem
import tj.humo.currencyconvertor.databinding.ItemExchangerBinding
import tj.humo.currencyconvertor.databinding.ItemNbtRateBinding
import kotlin.math.floor

class ExchangersViewHolder(private val binding: ItemExchangerBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: ExchangerItem, listener: (ExchangerItem) -> Unit) {
        with(binding) {

            root.setOnClickListener {
                listener(item)
            }

            Glide.with(imageViewBankIcon)
                .load(item.icon)
                .circleCrop()
                .placeholder(R.drawable.flag_emty)
                .error(R.drawable.flag_error)
                .into(imageViewBankIcon)

            textViewBankName.text = item.bankName

            // TODO
            /*textViewValue.text = String.format("%.6f", item.value / item.nominal) + " c."

            textViewCurrencyName.text = item.name
            textViewCurrencyFullName.text = item.fullName*/
        }
    }
}