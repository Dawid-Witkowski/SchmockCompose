package com.example.featureproductlist

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.util.Resource
import com.example.coredata.data.models.appproduct.Product
import com.example.coredata.data.remote.repository.FakeShopRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedProductViewModel @Inject constructor(
    private val repository: FakeShopRepository
): ViewModel() {

    var productList = mutableStateOf<List<Product>>(listOf())
    var loadErrorMessage = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    private var cachedProductList = listOf<Product>()
    private var isSearchStarting = true // only true if the search field is empty

    var productToDisplay: Product? = null
        private set

    init {
        getAllProducts()
    }

    fun searchProductList(query: String) {
        val listToSearch = if(isSearchStarting) productList.value else cachedProductList
        viewModelScope.launch(Dispatchers.Default) {
            if(query.isEmpty()) {
                productList.value = cachedProductList
                isSearchStarting = true
                return@launch
            }
            val searchResults = listToSearch.filter {product ->
                product.title.startsWith(query.trim(), ignoreCase = true)
            }
            if(isSearchStarting) {
                cachedProductList = productList.value
                isSearchStarting = false
            }
            productList.value = searchResults
        }
    }

    private fun getAllProducts(){
        viewModelScope.launch {
            isLoading.value = true
            val result = repository.getAllProductsList()
            when(result.status) {
                Resource.Status.ERROR -> {
                    // I know !! is not normally used BUT I HANDLED THAT in
                    // FakeShopRepositoryImpl, so please take that into consideration
                    loadErrorMessage.value = result.message!!
                    isLoading.value = false
                }
                Resource.Status.SUCCESS -> {
                    // using .!! would here be reasonable, but if the API somehow messes up and
                    // responds with no products I don't want the app to crash
                    productList.value = result.data ?: emptyList()
                    isLoading.value = false
                    loadErrorMessage.value = ""
                }
            }
        }
    }

    fun selectProductToDisplay(product: Product) {
        productToDisplay = product
    }

}