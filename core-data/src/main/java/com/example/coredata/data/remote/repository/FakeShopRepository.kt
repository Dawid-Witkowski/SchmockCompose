package com.example.coredata.data.remote.repository

import com.example.coredata.data.models.appproduct.Product

interface FakeShopRepository {
    suspend fun getAllProductsList(): List<Product>
}
