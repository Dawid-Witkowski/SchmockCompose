package com.example.featureproductlist.navigation.di

import com.example.featureproductlist.navigation.ProductModuleApi
import com.example.featureproductlist.navigation.ProductModuleApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object ProductNavModule {
    // Why tf doesn't bind work here? check it again
    @Provides
    fun provideProductApi(): ProductModuleApi {
        return ProductModuleApiImpl()
    }
}
