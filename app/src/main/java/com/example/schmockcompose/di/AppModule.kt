package com.example.schmockcompose.di

import com.example.coredata.di.ApiModule
import com.example.featureproductlist.navigation.ProductModuleApi
import com.example.schmockcompose.navigation.NavigationProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [ApiModule::class])
@InstallIn(SingletonComponent::class)
object AppModule {
    // I feel like this could be done in a... better way?
    @Provides
    fun provideNavigationProvider(productModuleApi: ProductModuleApi): NavigationProvider {
        return NavigationProvider(productModule = productModuleApi)
    }
}
