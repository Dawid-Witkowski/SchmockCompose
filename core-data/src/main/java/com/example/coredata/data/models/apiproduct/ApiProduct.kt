package com.example.coredata.data.models.apiproduct

import com.example.coredata.data.models.appproduct.Product

data class ApiProduct(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val rating: Rating,
    val title: String
)

fun ApiProduct.toProduct(): Product {
    return Product(
        category = this.category,
        description = this.description,
        id = this.id,
        image = this.image,
        price = this.price,
        rating = this.rating,
        title = this.title,
        size = "S" // default value, could also be done with string resource to support multiple languages
    )
}
