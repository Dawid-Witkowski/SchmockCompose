package com.example.coredata.data.remote

import com.example.coredata.data.models.apiproduct.ApiProduct
import retrofit2.http.GET

interface FakeShopApi {
    @GET("products")
    suspend fun getAllProductsList(): List<ApiProduct>
}
