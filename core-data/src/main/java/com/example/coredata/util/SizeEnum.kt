package com.example.coredata.util

enum class SizeEnum(val text: String) {
    SMALL("S"),
    MEDIUM("M"),
    LARGE("L")
}

fun getSizesAsList(): List<String> {
    return SizeEnum.values().map { size ->
        size.text
    }
}
