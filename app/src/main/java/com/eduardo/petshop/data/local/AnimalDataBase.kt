package com.eduardo.petshop.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.eduardo.petshop.data.local.dao.AnimalDao
import com.eduardo.petshop.data.local.entity.AnimalEntity

@Database(entities = [AnimalEntity::class], version = 1)

abstract class AnimalDataBase: RoomDatabase(){
    abstract val dao: AnimalDao

    companion object{
        const val name = "animal_db"
    }
}