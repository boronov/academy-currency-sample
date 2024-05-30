package tj.humo.currencyconvertor.ui.nbtRates.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tj.humo.currencyconvertor.data.remote.models.NbtRateItem
import tj.humo.currencyconvertor.databinding.ItemNbtRateBinding
import tj.humo.currencyconvertor.domain.model.NbtRate

class NbtAdapter(
    private val dataSet: List<NbtRate>,
    private val listener: (item: NbtRate) -> Unit
) : RecyclerView.Adapter<NbtViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NbtViewHolder {
        val binding = ItemNbtRateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NbtViewHolder(binding)
    }

    override fun getItemCount(): Int = dataSet.size

    override fun onBindViewHolder(holder: NbtViewHolder, position: Int) {
        holder.bind(dataSet[position], listener)
    }
}