package com.example.finalparcialito.feature_people.domain

typealias Users = List<User>
typealias UserFavorites = List<UserFavorite>
class User (
    val id: Int,
    val title: String,
    val first_name: String,
    val last_name: String,
    val email: String,
    val cellphone: String,
    val picture: String,
    val city: String,
    val gender: String
)

class UserFavorite(
    val id: Int,
    val title: String,
    val first_name: String,
    val gender: String,
    val city : String,
    val email: String,
    val picture: String
)