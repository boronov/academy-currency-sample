package tj.humo.currencyconvertor.ui

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import tj.humo.currencyconvertor.R


fun ImageView.loadRoundedImageWithCache(url: String) {
    Glide.with(this)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .circleCrop()
        .placeholder(R.drawable.flag_emty)
        .error(R.drawable.flag_error)
        .into(this)
}

fun Double.toFormattedMoney(): String = String.format("%.6f", this) + " c."


fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}