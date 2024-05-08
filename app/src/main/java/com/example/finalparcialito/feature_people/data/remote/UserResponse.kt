package com.example.finalparcialito.feature_people.data.remote

import com.google.gson.annotations.SerializedName



typealias UsersResponse = List<UserResponse>

data class UserResponse (
    val name: Name,
    val email: String,
    val cell: String,
    val picture: Picture,
    val location: UserLocation,
    val gender : String
)

data class Name(
    @SerializedName("title")
    val title: String,
    @SerializedName("first")
    val firstName: String,
    @SerializedName("last")
    val lastName: String
)

data class Picture(
    val thumbnail: String
)


data class UserLocation(
   val city: String
)
