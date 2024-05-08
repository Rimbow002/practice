package com.example.finalparcialito.feature_people.data.local

import com.example.finalparcialito.MyApplication
import pe.edu.upc.eatsexplorer.core_database.AppDatabase

class UserDaoFactory private constructor(){
    companion object {

        fun getUserDao(): UserDao {
            return AppDatabase.getInstance(MyApplication.getContext()).getUserDao()
        }
    }
}