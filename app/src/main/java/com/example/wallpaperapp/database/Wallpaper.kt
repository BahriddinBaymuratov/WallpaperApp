package com.example.wallpaperapp.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "WallpaperTable")
@Parcelize
data class Wallpaper(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val image: ByteArray
) : Parcelable{

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Wallpaper

        if (id != other.id) return false
        if (name != other.name) return false
        if (!image.contentEquals(other.image)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + image.contentHashCode()
        return result
    }
}
