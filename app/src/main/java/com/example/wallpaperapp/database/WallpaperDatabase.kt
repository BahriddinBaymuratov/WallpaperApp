package com.example.wallpaperapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Wallpaper::class], version = 1, exportSchema = false)
abstract class WallpaperDatabase: RoomDatabase() {
    abstract fun dao() : WallpaperDao

    companion object{
        @Volatile
        private var instance : WallpaperDatabase? = null
        operator fun invoke(context: Context) = instance ?: synchronized(Any()){
            instance ?: createDB(context).also {
                instance = it
            }
        }

        private fun createDB(context: Context) = Room.databaseBuilder(
            context,
            WallpaperDatabase::class.java,
            "Wallpaper.db"
        ).fallbackToDestructiveMigration()
            .build()
    }

}