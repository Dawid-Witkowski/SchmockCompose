package com.example.coredata.data.models.apiproduct

import com.example.coredata.data.models.appproduct.Product
import com.example.coredata.util.SizeEnum
import kotlin.random.Random
import kotlin.random.nextInt

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
        // unfortunately the api returns only one image per product, so here I'm "multiplying" the same image
        // 3-10 times to have something to display
        images = List(
            Random.nextInt(
                3..10
            )
        ) { index -> this.image },
        price = this.price,
        rating = this.rating,
        title = this.title,
        size = SizeEnum.SMALL // default value, could also be done with string resource to support multiple languages
    )
}
