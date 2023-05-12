package com.example.coredata.data.models.apiproduct

import kotlinx.serialization.Serializable

@Serializable
data class Rating(
    val count: Int,
    val rate: Double
)
