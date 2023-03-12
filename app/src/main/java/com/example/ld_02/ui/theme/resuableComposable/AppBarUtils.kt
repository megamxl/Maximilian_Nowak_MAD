package com.example.ld_02.ui.theme.resuableComposable

import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ld_02.navigation.Screens


@Composable
fun TOpAppBarWithBackButton(title: String, navController: NavController) {
    TopAppBar(
        elevation = 4.dp,
        title = {
            Text(title)
        },
        backgroundColor = MaterialTheme.colors.primarySurface,
        navigationIcon = {
            IconButton(onClick = { navController.navigate(Screens.Home.route) }) {
                Icon(Icons.Filled.ArrowBack, null)
            }
        })
}

@Composable
fun TopBarWithFavorites(navController: NavController) {
    var isFavoritesCollapsed by remember {
        mutableStateOf(false)
    }

    TopAppBar(
        elevation = 4.dp,
        title = {
            Text("My Movie Palace")
        },
        backgroundColor = MaterialTheme.colors.primarySurface,
        actions = {
            IconButton(
                onClick = {
                    isFavoritesCollapsed = true
                },
            ) {
                Icon(Icons.Filled.MoreVert, null)
            }
            DropdownMenu(
                expanded = isFavoritesCollapsed,
                onDismissRequest = {
                    isFavoritesCollapsed = false
                }
            ) {
                DropdownMenuItem(onClick = { navController.navigate(Screens.Favorites.route) }) {
                    Row {
                        Icon(imageVector = Icons.Rounded.Favorite, contentDescription = null)
                        Text(text = " Favorites")
                    }
                }
            }
        })
}
