package com.example.coredata.data.models.appproduct

import com.example.coredata.data.models.apiproduct.Rating
import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val rating: Rating,
    val title: String,
    val size: String // I really wanted the size value, all I can say
)
