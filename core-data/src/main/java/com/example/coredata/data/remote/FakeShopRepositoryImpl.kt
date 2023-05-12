package com.example.coredata.data.remote

import com.example.coredata.data.models.apiproduct.toProduct
import com.example.coredata.data.models.appproduct.Product
import com.example.coredata.data.remote.repository.FakeShopRepository
import javax.inject.Inject

class FakeShopRepositoryImpl @Inject constructor(
    private val endpoint: FakeShopApi
) : FakeShopRepository {
    override suspend fun getAllProductsList(): List<Product> {
        return endpoint.getAllProductsList().map { it.toProduct() }
    }
}
