package com.eduardo.petshop.data.di

import android.content.Context
import androidx.room.Room
import com.eduardo.petshop.data.local.AnimalDataBase
import com.eduardo.petshop.data.local.repository.AnimalRepositoryEmpl
import com.eduardo.petshop.domain.repository.PetRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesAnimalDataBase(@ApplicationContext context: Context): AnimalDataBase =
        Room.databaseBuilder(
            context,
            AnimalDataBase::class.java,
            AnimalDataBase.name
        ).build()

    @Provides
    @Singleton
    fun providesPetRepository(dataBase: AnimalDataBase): PetRepository =
        AnimalRepositoryEmpl(dao = dataBase.dao)

}