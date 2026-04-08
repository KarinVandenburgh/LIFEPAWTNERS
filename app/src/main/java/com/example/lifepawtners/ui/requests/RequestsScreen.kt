package com.example.lifepawtners.ui.requests

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Message
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lifepawtners.R
import com.example.lifepawtners.ui.data.local.DatabaseProvider
import com.example.lifepawtners.ui.data.model.AdoptionRequest
import com.example.lifepawtners.ui.profile.ProfileTab
import com.example.lifepawtners.ui.search.SearchScreen
import androidx.compose.ui.platform.LocalContext

private val Coral = Color(0xFFFF6150)
private val SoftBackground = Color(0xFFF8F8FC)
private val CardBackground = Color(0xFFFFFFFF)
private val BubbleBackground = Color(0xFFFFF1EE)
private val DarkText = Color(0xFF24324A)
private val MutedText = Color(0xFF6B7280)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RequestsScreen(
    navController: NavController,
    currentOwnerEmail: String,
    modifier: Modifier = Modifier
) {
    var selectedItem by remember { mutableIntStateOf(0) }
    var searchText by remember { mutableStateOf("") }

    val context = LocalContext.current
    val db = DatabaseProvider.getDatabase(context)
    val requestDao = db.adoptionRequestDao()

    var requestList by remember { mutableStateOf<List<AdoptionRequest>>(emptyList()) }

    LaunchedEffect(Unit) {
        requestList = requestDao.getRequestsForOwner(currentOwnerEmail)
    }

    val filteredRequests = requestList.filter {
        it.senderName.contains(searchText, ignoreCase = true) ||
                it.petName.contains(searchText, ignoreCase = true) ||
                it.messageText.contains(searchText, ignoreCase = true)
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    androidx.compose.foundation.Image(
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
                            contentDescription = "Requests",
                            tint = Coral
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
                            tint = Coral
                        )
                    }
                )

                NavigationBarItem(
                    selected = selectedItem == 2,
                    onClick = { selectedItem = 2 },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.AddCircle,
                            contentDescription = "Add Pet",
                            tint = Coral
                        )
                    }
                )

                NavigationBarItem(
                    selected = selectedItem == 3,
                    onClick = { selectedItem = 3 },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Message,
                            contentDescription = "Messages",
                            tint = Coral
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
                            tint = Coral
                        )
                    }
                )
            }
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = SoftBackground
        ) {
            when (selectedItem) {
                0 -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .navigationBarsPadding()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "Requests",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            color = DarkText
                        )

                        Text(
                            text = "Review messages from people interested in your pets.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MutedText,
                            modifier = Modifier.padding(top = 6.dp, bottom = 16.dp)
                        )

                        OutlinedTextField(
                            value = searchText,
                            onValueChange = { searchText = it },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            placeholder = {
                                Text("Search requests")
                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.Search,
                                    contentDescription = "Search"
                                )
                            }
                        )

                        if (filteredRequests.isEmpty()) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("No requests yet")
                            }
                        } else {
                            LazyColumn(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                items(filteredRequests) { request ->
                                    RequestCard(request = request)
                                }
                            }
                        }
                    }
                }

                1 -> SearchScreen()

                2 -> Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Add Pet tab")
                }

                3 -> Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Messages tab")
                }

                4 -> ProfileTab(navController)
            }
        }
    }
}

@Composable
fun RequestCard(request: AdoptionRequest) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = request.senderName,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = DarkText
            )

            Text(
                text = "Interested in ${request.petName} (${request.petBreed})",
                style = MaterialTheme.typography.bodySmall,
                color = MutedText,
                modifier = Modifier.padding(top = 4.dp, bottom = 12.dp)
            )

            MessagePreviewBubble(message = request.messageText)
        }
    }
}

@Composable
fun MessagePreviewBubble(message: String) {
    androidx.compose.foundation.layout.Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = BubbleBackground,
                shape = androidx.compose.foundation.shape.RoundedCornerShape(18.dp)
            )
            .padding(12.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .padding(end = 8.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.Message,
                contentDescription = "Message preview",
                tint = Coral
            )
        }

        Text(
            text = message,
            style = MaterialTheme.typography.bodyMedium,
            color = DarkText
        )
    }
}