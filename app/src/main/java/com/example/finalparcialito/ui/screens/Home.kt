package com.example.finalparcialito.ui.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.practicaparcial.navigation.Routes

@Composable
fun Home() {
    val navController = rememberNavController()

    Scaffold (){ paddingValues->
        NavHost(navController = navController, startDestination = Routes.Home,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Routes.Home) {
                HomeScreen{
                    navController.navigate(it)
                }
            }
            composable(Routes.PeopleScreen) {
                PeopleScreen ( navigate={destination->
                    navController.navigate(destination)
                })
            }
            composable(Routes.Favorites) {
                FavoriteScreen ( navigate={destination->
                    navController.navigate(destination)
                })
            }


        }

    }
}

@Composable
fun HomeScreen(navigateTo: (String)->Unit){
    Scaffold {paddingValues->
        Row(
            modifier = Modifier.padding(paddingValues)
        ) {
            Button(onClick = {navigateTo(Routes.PeopleScreen)}){
                Text("People")
            }
            Button(onClick = {navigateTo(Routes.Favorites)}){
                Text("Favorites")
            }
        }
    }
}