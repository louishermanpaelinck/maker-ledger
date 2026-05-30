package com.makerledger.data
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.makerledger.model.Material
import com.makerledger.model.Project

@Database(entities = [Material::class, Project::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun materialDao(): MaterialDao
    abstract fun projectDao(): ProjectDao
}
