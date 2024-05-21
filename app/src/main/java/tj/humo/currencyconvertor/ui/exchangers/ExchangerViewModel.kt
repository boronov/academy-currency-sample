package tj.humo.currencyconvertor.ui.exchangers

import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tj.humo.currencyconvertor.data.RetrofitApi
import tj.humo.currencyconvertor.data.models.ExchangerItem

class ExchangerViewModel : ViewModel() {
    var isLoading: Boolean = false
    var errorMessage: String? = null
    var dataSet: List<ExchangerItem> = emptyList()

    fun loadNbtRates(
        onError: (message: String?) -> Unit,
        onLoading: (isLoading: Boolean) -> Unit,
        onSuccess: (dataSet: List<ExchangerItem>) -> Unit
    ) {
        isLoading = true
        onLoading(isLoading)


        RetrofitApi.getExchangersRate().enqueue(object : Callback<List<ExchangerItem>> {
            override fun onResponse(
                p0: Call<List<ExchangerItem>>,
                p1: Response<List<ExchangerItem>>
            ) {
                isLoading = false
                onLoading(isLoading)

                if (p1.isSuccessful) {
                    errorMessage = null
                    dataSet = p1.body() ?: emptyList()
                    onSuccess(dataSet)
                } else {
                    errorMessage = "Что-то пошло не так"
                    onError(errorMessage)
                }
            }

            override fun onFailure(p0: Call<List<ExchangerItem>>, p1: Throwable) {
                isLoading = false
                onLoading(isLoading)

                errorMessage = p1.message
                onError(errorMessage)
            }
        })
    }
}