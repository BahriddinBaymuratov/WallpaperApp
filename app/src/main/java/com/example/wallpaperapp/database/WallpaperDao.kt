package com.example.wallpaperapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WallpaperDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE )
    fun saveImg(wallpaper: Wallpaper)

    @Query("SELECT * FROM WallpaperTable ORDER BY name DESC")
    fun getAllImg(): List<Wallpaper>
}