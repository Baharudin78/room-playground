package com.baharudin.latianroom.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "user_table")
@Parcelize
data class User (
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val namaDepan : String,
    val namaBelakang : String,
    val umur : Int
        ):Parcelable