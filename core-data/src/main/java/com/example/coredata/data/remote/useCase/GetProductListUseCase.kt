package com.example.coredata.data.remote.useCase

import com.example.core.util.Resource
import com.example.coredata.data.remote.repository.FakeShopRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetProductListUseCase @Inject constructor(private val repository: FakeShopRepository) {
    operator fun invoke() = flow {
        emit(Resource.loading())
        emit(Resource.success(repository.getAllProductsList()))
    }.catch {
        emit(Resource.error(it.message.toString()))
    }.flowOn(Dispatchers.IO)
}
