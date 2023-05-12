package com.example.core.util

data class Resource<out T>(val status: Status, val data: T?, val message: StringValue) {
    enum class Status {
        LOADING,
        SUCCESS,
        ERROR
    }

    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, StringValue.Empty)
        }

        fun <T> error(message: StringValue): Resource<T> {
            return Resource(Status.ERROR, null, message)
        }

        fun <T> loading(): Resource<T> {
            return Resource(Status.LOADING, null, StringValue.Empty)
        }
    }
}
