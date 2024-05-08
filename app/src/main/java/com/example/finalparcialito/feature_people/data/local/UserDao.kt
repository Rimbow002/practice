package com.example.finalparcialito.feature_people.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.finalparcialito.feature_people.domain.UserFavorites

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getAll(): UserFavorites

    @Query("SELECT * FROM users WHERE id = :id")
    fun getById(id: Int): UserEntity?

    @Insert
    fun insert(userEntity: UserEntity)

    @Query("DELETE FROM users WHERE id = :id")
    fun deleteById(id: Int)
}