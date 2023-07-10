package com.example.coredata.di

import com.example.core.Const
import com.example.coredata.data.remote.FakeShopApi
import com.example.coredata.data.remote.FakeShopRepositoryImpl
import com.example.coredata.data.remote.repository.FakeShopRepository
import com.example.coredata.data.remote.useCase.GetProductListUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ApiModule {
    @Binds
    abstract fun bindRepository(endpointImpl: FakeShopRepositoryImpl): FakeShopRepository

    companion object {
        @Provides
        @Singleton
        fun provideShopApi(): FakeShopApi {
            return Retrofit
                .Builder()
                .baseUrl(Const.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(FakeShopApi::class.java)
        }

        @Provides
        fun provideProductUseCase(productRepository: FakeShopRepository): GetProductListUseCase {
            return GetProductListUseCase(productRepository)
        }
    }
}
