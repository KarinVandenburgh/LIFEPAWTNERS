package com.example.lifepawtners.ui.main


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
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
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.ui.layout.ContentScale
import androidx.compose.material3.NavigationBarItemDefaults

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun MainScreen() {
    var selectedItem by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Image(
                        painter = painterResource(id = R.drawable.logo_words),
                        contentDescription = "Logo"
                    )
                }
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = selectedItem == 0,
                    onClick = { selectedItem = 0 },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Home",
                            tint = Color(0xFFFF6150)
                        )
                    }
                )
                NavigationBarItem(
                    selected = selectedItem == 1,
                    onClick = { selectedItem = 1 },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = Color(0xFFFF6150)
                        )
                    }
                )
                NavigationBarItem(
                    selected = selectedItem == 2,
                    onClick = { selectedItem = 2 },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.StarOutline,
                            contentDescription = "Saved",
                            tint = Color(0xFFFF6150)
                        )
                    }
                )
                NavigationBarItem(
                    selected = selectedItem == 3,
                    onClick = { selectedItem = 3 },
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Message,
                            contentDescription = "Messages",
                            tint = Color(0xFFFF6150)
                        )
                    }

                )
                NavigationBarItem(
                    selected = selectedItem == 4,
                    onClick = { selectedItem = 4 },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "Profile",
                            tint = Color(0xFFFF6150)
                        )
                    }
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            when (selectedItem) {
                0 -> SwipeScreen()
                1 -> Text("Search tab")
                2 -> Text("Saved tab")
                3 -> MessagesScreen()
                4 -> Text("Profile tab")
            }
        }
    }
}
/*settings bar if needed
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

*/