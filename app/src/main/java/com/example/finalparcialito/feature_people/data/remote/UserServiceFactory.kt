package com.example.finalparcialito.feature_people.data.remote

import com.example.finalparcialito.core_network.RetrofitFactory

class UserServiceFactory private constructor(){
    companion object{
        fun getUserService(): UserService {
            return RetrofitFactory.getRetrofit().create(UserService::class.java)
        }

    }
}