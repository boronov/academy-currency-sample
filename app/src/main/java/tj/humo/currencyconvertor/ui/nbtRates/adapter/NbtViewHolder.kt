package tj.humo.currencyconvertor.ui.nbtRates.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import tj.humo.currencyconvertor.R
import tj.humo.currencyconvertor.data.models.NbtRateItem
import tj.humo.currencyconvertor.databinding.ItemNbtRateBinding
import kotlin.math.floor

class NbtViewHolder(private val binding: ItemNbtRateBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: NbtRateItem, listener: (NbtRateItem) -> Unit) {
        with(binding) {

            root.setOnClickListener {
                listener(item)
            }
            Glide.with(imageViewFlag)
                .load(item.flag)
                .circleCrop()
                .placeholder(R.drawable.flag_emty)
                .error(R.drawable.flag_error)
                .into(imageViewFlag)

            textViewValue.text = String.format("%.6f", item.value / item.nominal) + " c."

            textViewCurrencyName.text = item.name
            textViewCurrencyFullName.text = item.fullName
        }
    }
}