package tj.humo.currencyconvertor.ui.exchangers.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tj.humo.currencyconvertor.data.models.ExchangerItem
import tj.humo.currencyconvertor.databinding.ItemCurrencyBinding
import tj.humo.currencyconvertor.ui.exchangers.adapter.CurrencyAdapter.ViewHolder.Companion.VIEW_TYPE
import tj.humo.currencyconvertor.ui.loadRoundedImageWithCache
import tj.humo.currencyconvertor.ui.toFormattedMoney

class CurrencyAdapter(
    private val dataSet: List<ExchangerItem.Currency>,
    private val listener: (item: ExchangerItem.Currency) -> Unit
) : RecyclerView.Adapter<CurrencyAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemCurrencyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemViewType(position: Int): Int {
        return VIEW_TYPE
    }

    override fun getItemCount(): Int = dataSet.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataSet[position], listener)
    }

    class ViewHolder(private val binding: ItemCurrencyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ExchangerItem.Currency, listener: (item: ExchangerItem.Currency) -> Unit) {
            binding.root.setOnClickListener {
                listener(item)
            }

            binding.imageViewCurrencyIcon.loadRoundedImageWithCache(item.flag)

            binding.textViewCurrencyName.text = item.name

            binding.textViewBuy.text = item.buyValue.toFormattedMoney()
            binding.textViewSell.text = item.sellValue.toFormattedMoney()
        }

        companion object {
            const val VIEW_TYPE = 2
        }
    }
}