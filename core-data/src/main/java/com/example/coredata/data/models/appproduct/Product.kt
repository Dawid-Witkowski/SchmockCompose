package com.example.coredata.data.models.appproduct

import com.example.coredata.data.models.apiproduct.Rating
import com.example.coredata.util.SizeEnum
import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val category: String,
    val description: String,
    val id: Int,
    val images: List<String>,
    val price: Double,
    val rating: Rating,
    val title: String,
    val size: SizeEnum // I really wanted the size value, all I can say
)
