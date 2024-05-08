package com.example.finalparcialito.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finalparcialito.feature_people.data.repository.UserRepository
import com.example.finalparcialito.feature_people.domain.UserFavorite
import com.example.practicaparcial.navigation.Routes


@Composable
fun FavoriteScreen(navigate : (String) -> Unit = {},
                 userRepository: UserRepository = UserRepository()
                 ){

    val people = remember{
        mutableStateOf(emptyList<UserFavorite>())
    }


    userRepository.getAllFavorites() {
        people.value = it
    }

    Scaffold { paddingValues->
        Column(modifier = Modifier.padding(paddingValues)) {

        Button(
            onClick = { navigate(Routes.Home) },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text("Go to Home")
        }
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(people.value) { person ->
                FavoriteItem(person = person, onDelete = {
                    userRepository.delete(person.id)

                }
                )
            }
        }
        }

    }
}

@Composable
fun FavoriteItem(person: UserFavorite, onDelete: () -> Unit = {}) {
    var isdeleted by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        content = {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {

                BoldText("Name: ${person.title} ${person.first_name}")
                Spacer(modifier = Modifier.height(4.dp))
                BoldText("City: ${person.city}")
                Spacer(modifier = Modifier.height(4.dp))
                BoldText("Gender: ${person.gender}")
                Spacer(modifier = Modifier.height(4.dp))
                BoldText("City: ${person.city}")
                Spacer(modifier = Modifier.height(8.dp))
                PersonImage(url = person.picture)
                Button(
                    onClick = {
                        isdeleted = true
                        onDelete()
                    },
                    enabled = !isdeleted
                ) {
                    Text(if (isdeleted) "Deleted" else "Delete from favorites")
                }
            }

        }
    )
}


@Preview
@Composable
fun FavoritePreview(){
    FavoriteScreen(navigate = {})
}