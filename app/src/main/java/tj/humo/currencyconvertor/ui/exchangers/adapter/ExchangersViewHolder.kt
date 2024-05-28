package tj.humo.currencyconvertor.ui.exchangers.adapter

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.RecycledViewPool
import com.bumptech.glide.Glide
import tj.humo.currencyconvertor.R
import tj.humo.currencyconvertor.data.remote.models.ExchangerItem
import tj.humo.currencyconvertor.data.remote.models.NbtRateItem
import tj.humo.currencyconvertor.databinding.ItemExchangerBinding
import tj.humo.currencyconvertor.databinding.ItemNbtRateBinding
import kotlin.math.floor

class ExchangersViewHolder(
    private val binding: ItemExchangerBinding,
    private val viewPool: RecycledViewPool
) :
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

            recyclerViewCurrency.setRecycledViewPool(viewPool)
            recyclerViewCurrency.adapter = CurrencyAdapter(item.currency) {

            }
        }
    }

    companion object {
        const val VIEW_TYPE = 1
    }
}