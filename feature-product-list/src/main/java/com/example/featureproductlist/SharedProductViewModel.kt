package com.example.featureproductlist

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.util.Resource
import com.example.coredata.data.models.appproduct.Product
import com.example.coredata.data.remote.useCase.GetProductListUseCase
import com.example.featureproductlist.productList.ProductListStateHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedProductViewModel @Inject constructor(
    private val getProductListUseCase: GetProductListUseCase
) : ViewModel() {

    private val _state = mutableStateOf(ProductListStateHolder())
    val state: State<ProductListStateHolder> get() = _state

    // keeping cache in any form > calling the api on every search, also does not require debouncing
    private var cachedProductList = listOf<Product>()

    // product that will get displayed when the user clicks on a product
    lateinit var productToDisplay: Product

    init {
        getAllProducts()
    }

    fun searchProductList(query: String) {
        val searchResults = cachedProductList.filter { product ->
            product.title.startsWith(query.trim(), ignoreCase = true)
        }
        _state.value = ProductListStateHolder(
            currentlyDisplayedProductList = searchResults
        )
    }


    private fun getAllProducts() = viewModelScope.launch {
        getProductListUseCase().onEach {
            when (it.status) {
                Resource.Status.LOADING -> {
                    _state.value = ProductListStateHolder(isLoading = true)
                }

                Resource.Status.SUCCESS -> {
                    _state.value = ProductListStateHolder(
                        isLoading = false,
                        currentlyDisplayedProductList = it.data ?: emptyList()
                    )
                    cachedProductList = it.data ?: emptyList()
                }

                Resource.Status.ERROR -> {
                    _state.value =
                        ProductListStateHolder(isLoading = false, error = it.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }


    fun selectProductToDisplay(product: Product) {
        productToDisplay = product
    }
}
