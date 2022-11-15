package com.example.wallpaperapp.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
data class Image(
    val name:String,
    val image:Int,
): Parcelable
