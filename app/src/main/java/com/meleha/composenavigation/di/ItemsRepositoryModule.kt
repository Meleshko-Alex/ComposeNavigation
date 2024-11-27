package com.meleha.composenavigation.di

import com.meleha.composenavigation.ItemsRepository
import com.meleha.composenavigation.ItemsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ItemsRepositoryModule  {

    @Binds
    fun itemRepository(impl: ItemsRepositoryImpl): ItemsRepository
}