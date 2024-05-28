package tj.humo.currencyconvertor.ui.exchangers.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tj.humo.currencyconvertor.data.remote.models.ExchangerItem
import tj.humo.currencyconvertor.databinding.ItemExchangerBinding

class ExchangersAdapter(
    private val dataSet: List<ExchangerItem>,
    private val listener: (item: ExchangerItem) -> Unit
) : RecyclerView.Adapter<ExchangersViewHolder>() {

    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExchangersViewHolder {
        val binding =
            ItemExchangerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExchangersViewHolder(binding, viewPool)
    }

    override fun getItemCount(): Int = dataSet.size

    override fun getItemViewType(position: Int): Int {
        return ExchangersViewHolder.VIEW_TYPE
    }

    override fun onBindViewHolder(holder: ExchangersViewHolder, position: Int) {
        holder.bind(dataSet[position], listener)
    }
}