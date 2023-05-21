package com.example.coredata.data.remote

import com.example.core.util.Resource
import com.example.coredata.data.models.apiproduct.toProduct
import com.example.coredata.data.models.appproduct.Product
import com.example.coredata.data.remote.repository.FakeShopRepository
import javax.inject.Inject

class FakeShopRepositoryImpl @Inject constructor(
    private val endpoint: FakeShopApi
) : FakeShopRepository {
    override suspend fun getAllProductsList(): Resource<List<Product>> {
        val response = try {
            endpoint.getAllProductsList()
        } catch (e: Exception) {
            return Resource.error(e.message ?: "An unknown error occurred")
        }
        return Resource.success(response.map { it.toProduct() })
    }
}
