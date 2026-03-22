package com.example.lifepawtners.ui.main

import androidx.compose.foundation.content.MediaType.Companion.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lifepawtners.R
import com.example.lifepawtners.ui.chat.MessagesScreen
import com.example.lifepawtners.ui.swipe.SwipeScreen
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.ui.layout.ContentScale

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun MainScreen() {
    var selectedItem by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        //horizontal logo
                        Image(
                            painter = painterResource(id = R.drawable.logo_words),
                            contentDescription = "Logo",
                            modifier = Modifier.width(180.dp).height(40.dp),
                            contentScale = ContentScale.Fit
                        )
                    }
                },
                actions = {
                    //Settings button (on right)
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings"
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = selectedItem == 0,
                    onClick = { selectedItem = 0 },
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.pawtnerslogo),
                            contentDescription = "Saved",
                            tint = Color.Unspecified
                        )
                    }
                )

                NavigationBarItem(
                    selected = selectedItem == 1,
                    onClick = { selectedItem = 1 },
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_search),
                            contentDescription = "Search",
                            tint = Color.Unspecified
                        )
                    }
                )

                NavigationBarItem(
                    selected = selectedItem == 2,
                    onClick = { selectedItem = 2 },
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_heartsave),
                            contentDescription = "Saved",
                            tint = Color.Unspecified
                        )
                    }
                )

                NavigationBarItem(
                    selected = selectedItem == 3,
                    onClick = { selectedItem = 3 },
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_message),
                            contentDescription = "Messages",
                            tint = Color.Unspecified
                        )
                    }
                )

                NavigationBarItem(
                    selected = selectedItem == 4,
                    onClick = { selectedItem = 4 },
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_profile),
                            contentDescription = "Messages",
                            tint = Color.Unspecified
                        )
                    }
                )
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize().padding(innerPadding)
        ){
        when (selectedItem){
                    0 -> SwipeScreen()
                    1 -> SearchScreen()
                    2 -> SavedScreen()
                    3 -> MessagesScreen()
                    4 -> ProfileScreen()
                }
            }
        }
    }
    @Composable
    fun SavedScreen(modifier: Modifier = Modifier) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Saved Screen")
        }
    }

    @Composable
    fun SearchScreen(modifier: Modifier = Modifier) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Search Screen")
        }
    }

    @Composable
    fun ProfileScreen(modifier: Modifier = Modifier) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Profile Screen")
        }
    }

