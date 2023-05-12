package com.example.schmockcompose.di

import com.example.coredata.di.ApiModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

// This only serves as a "grouping" module for future feature-related modules
@Module(includes = [ApiModule::class])
@InstallIn(SingletonComponent::class)
object AppModule
