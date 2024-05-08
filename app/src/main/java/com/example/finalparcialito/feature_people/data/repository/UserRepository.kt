package com.example.finalparcialito.feature_people.data.repository

import android.util.Log
import com.example.finalparcialito.feature_people.data.local.UserDao
import com.example.finalparcialito.feature_people.data.local.UserDaoFactory
import com.example.finalparcialito.feature_people.data.local.UserEntity
import com.example.finalparcialito.feature_people.data.remote.ApiResponse
import com.example.finalparcialito.feature_people.data.remote.UserService
import com.example.finalparcialito.feature_people.data.remote.UserServiceFactory
import com.example.finalparcialito.feature_people.domain.User
import com.example.finalparcialito.feature_people.domain.UserFavorite
import com.example.finalparcialito.feature_people.domain.UserFavorites
import com.example.finalparcialito.feature_people.domain.Users
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response




class UserRepository (
    private val userService: UserService = UserServiceFactory.getUserService(),
    private val userDao : UserDao = UserDaoFactory.getUserDao()
)
{
    fun insert (user: User){
        userDao.insert(UserEntity(
            title = user.title,
            first_name = user.first_name,
            gender= user.gender,
            city = user.city,
            email = user.email,
            picture = user.picture
        )
        )
    }

    fun delete (id : Int){
        userDao.deleteById(id)
    }

    fun getAllFavorites(callback: (UserFavorites) -> Unit) {
        val users = userDao.getAll()
        val usersList: UserFavorites = users.map { user ->
            UserFavorite(
                id = user.id,
                title = user.title,
                first_name = user.first_name,
                gender = user.gender,
                city = user.city,
                email = user.email,
                picture = user.picture
            )
        }
        callback(usersList)
    }


    fun getAllUserApi(callback: (Users) ->Unit, cantidad: Int){
        val getAll = userService.getAll(cantidad)

        getAll.enqueue(object: Callback<ApiResponse>
        {

            override fun onResponse(
                call: Call<ApiResponse>,
                response: Response<ApiResponse>
            )
            {
                val gson = GsonBuilder().setPrettyPrinting().create()
                val jsonResponse = gson.toJson(response.body())
                Log.d("responsa", jsonResponse)
                if(response.isSuccessful)
                {
                    Log.d("respuesta", "Se obtuvo la respuesta exitosamente")

                    val data = response.body() as ApiResponse
                    val usersResponse = data.results
                    var users: Users = arrayListOf()
                    Log.d("responsse", response.body().toString())

                    for (userResponse in usersResponse) {
                        Log.d("UserRepository", "UserResponse: $userResponse")
                        users = users + User(
                            id = 1,
                            title = userResponse.name.title ,
                            first_name = userResponse.name.firstName,
                            last_name = userResponse.name.lastName,
                            email = userResponse.email,
                            cellphone = userResponse.cell,
                            picture = userResponse.picture.thumbnail,
                            city = userResponse.location.city,
                            gender = userResponse.gender
                        )
                        Log.d("UserRepository", "User: $users")
                    }
                    callback(users)
                }else {
                    Log.d("UserRepository", "Error en la respuesta: ${response.errorBody()}")
                }

            }


            override fun onFailure(p0: Call<ApiResponse>, p1: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

}
