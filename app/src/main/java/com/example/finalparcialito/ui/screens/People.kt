package com.example.finalparcialito.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.finalparcialito.feature_people.data.repository.UserRepository
import com.example.finalparcialito.feature_people.domain.User
import com.example.practicaparcial.navigation.Routes


@Composable
fun PeopleScreen(navigate : (String) -> Unit = {},
                 userRepository: UserRepository = UserRepository()
){

    val people = remember{
        mutableStateOf(emptyList<User>())
    }

    val amount = remember {
        mutableStateOf("")
    }

    Scaffold { paddingValues->
        Column(modifier = Modifier.padding(paddingValues)) {
            TextField(
                value = amount.value,
                onValueChange = { amount.value = it },
                label = { Text("Enter number of people") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            Button(
                onClick = {
                    // Convertir el valor del TextField a Int
                    val cantidad = amount.value.toIntOrNull() ?: return@Button
                    userRepository.getAllUserApi({ users ->
                        people.value = users.take(cantidad)
                    }, cantidad = cantidad)
                },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text("Search")
            }

            Button(
                onClick = { navigate(Routes.Home) },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text("Go to Home")
            }

            LazyColumn(modifier = Modifier.padding(paddingValues)) {
                items(people.value)  { person ->
                    PersonItem(person = person, onAddToFavoriteClicked = {
                        userRepository.insert(person)
                    })
                }

            }



        }
    }
}

@Composable
fun PersonItem(person: User, onAddToFavoriteClicked: () -> Unit = {}) {
    var isAddedToFavorites by remember {
        mutableStateOf(false) }

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

                BoldText("Name: ${person.title} ${person.first_name} ${person.last_name}")
                Spacer(modifier = Modifier.height(4.dp))
                BoldText("Email: ${person.email}")
                Spacer(modifier = Modifier.height(4.dp))
                BoldText("Cellphone: ${person.cellphone}")
                Spacer(modifier = Modifier.height(4.dp))
                BoldText("City: ${person.city}")
                Spacer(modifier = Modifier.height(8.dp))
                PersonImage(url = person.picture)
                Button(
                    onClick = {
                        onAddToFavoriteClicked()
                        isAddedToFavorites = true
                    },
                    modifier = Modifier.padding(top = 8.dp),
                    enabled = !isAddedToFavorites
                ) {
                    Text(text = if (isAddedToFavorites) "Added to Favorites" else "Add to Favorites")
                }
            }

        }
    )
}

@Composable
fun BoldText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
    )
}

@Composable
fun PersonImage(url: String){
    val painter = rememberImagePainter(
        data = url,
        builder = {
            transformations(CircleCropTransformation())
            crossfade(true)
        }
    )

    Image(
        painter = painter,
        contentDescription = null, // A11y support: provide a meaningful description
        contentScale = ContentScale.Crop,
        modifier = Modifier.size(100.dp),
    )
}

@Preview
@Composable
fun PeopleScreenPreview(){
    PeopleScreen(navigate = {})
}