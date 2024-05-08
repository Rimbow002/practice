package com.example.finalparcialito.feature_people.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val first_name: String,
    val gender: String,
    val city : String,
    val email: String,
    val picture: String
)