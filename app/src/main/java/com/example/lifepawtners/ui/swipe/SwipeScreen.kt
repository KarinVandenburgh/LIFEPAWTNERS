package com.example.lifepawtners.ui.swipe

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.lifepawtners.ui.data.local.DatabaseProvider
import com.example.lifepawtners.ui.data.model.AdoptionRequest
import com.example.lifepawtners.ui.data.model.PetProfile
import kotlinx.coroutines.launch
import kotlin.math.roundToInt
import java.util.UUID
import androidx.compose.ui.platform.LocalContext

@Composable
fun SwipeScreen(
    petList: List<PetProfile> = emptyList(),
    currentUserEmail: String? = null,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val db = DatabaseProvider.getDatabase(context)
    val requestDao = db.adoptionRequestDao()
    val userDao = db.userAccountDao()
    val scope = rememberCoroutineScope()

    val filteredPets = remember(petList, currentUserEmail) {
        if (currentUserEmail == null) {
            petList
        } else {
            petList.filter { it.ownerEmail != currentUserEmail }
        }
    }

    var currentPetIndex by remember { mutableIntStateOf(0) }
    var offsetX by remember { mutableFloatStateOf(0f) }

    var showRequestDialog by remember { mutableStateOf(false) }
    var requestMessage by remember { mutableStateOf("") }

    val animatedOffsetX by animateFloatAsState(
        targetValue = offsetX,
        label = "card_offset"
    )

    val rotation = animatedOffsetX / 60f

    fun goToNextPet() {
        if (currentPetIndex < filteredPets.lastIndex) {
            currentPetIndex++
        }
        offsetX = 0f
    }

    suspend fun submitInterest(pet: PetProfile) {
        val cleanEmail = currentUserEmail ?: return
        val sender = userDao.getUserByEmail(cleanEmail)
        val senderName = if (sender != null) {
            "${sender.firstName} ${sender.lastName}"
        } else {
            cleanEmail
        }

        requestDao.insertRequest(
            AdoptionRequest(
                requestId = UUID.randomUUID().toString(),
                petId = pet.petId,
                petName = pet.name,
                petBreed = pet.breed,
                ownerEmail = pet.ownerEmail,
                senderEmail = cleanEmail,
                senderName = senderName,
                messageText = requestMessage.trim()
            )
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            if (filteredPets.isNotEmpty() && currentPetIndex < filteredPets.size) {
                val pet = filteredPets[currentPetIndex]

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset {
                            IntOffset(animatedOffsetX.roundToInt(), 0)
                        }
                        .graphicsLayer {
                            rotationZ = rotation
                        }
                        .pointerInput(currentPetIndex) {
                            detectDragGestures(
                                onDrag = { change, dragAmount ->
                                    change.consumeAllChanges()
                                    offsetX += dragAmount.x
                                },
                                onDragEnd = {
                                    when {
                                        offsetX > 300f -> {
                                            requestMessage = "Hi, I am interested in ${pet.name}."
                                            showRequestDialog = true
                                            offsetX = 0f
                                        }
                                        offsetX < -300f -> {
                                            goToNextPet()
                                        }
                                        else -> {
                                            offsetX = 0f
                                        }
                                    }
                                }
                            )
                        }
                ) {
                    PetCard(pet = pet)
                }
            } else {
                Text("No more pets right now")
            }
        }

        ActionButtons(
            onNoClick = { goToNextPet() },
            onBookmarkClick = { },
            onYesClick = {
                if (filteredPets.isNotEmpty() && currentPetIndex < filteredPets.size) {
                    val pet = filteredPets[currentPetIndex]
                    requestMessage = "Hi, I am interested in ${pet.name}."
                    showRequestDialog = true
                }
            }
        )
    }

    if (showRequestDialog && filteredPets.isNotEmpty() && currentPetIndex < filteredPets.size) {
        val pet = filteredPets[currentPetIndex]

        AlertDialog(
            onDismissRequest = { showRequestDialog = false },
            title = {
                Text("Send interest message")
            },
            text = {
                Column {
                    Text("Message for ${pet.name}'s owner")
                    Spacer(modifier = Modifier.height(12.dp))
                    OutlinedTextField(
                        value = requestMessage,
                        onValueChange = { requestMessage = it },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = {
                            Text("Write a message")
                        }
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        scope.launch {
                            if (requestMessage.isNotBlank()) {
                                submitInterest(pet)
                                showRequestDialog = false
                                requestMessage = ""
                                goToNextPet()
                            }
                        }
                    }
                ) {
                    Text("Send")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showRequestDialog = false }
                ) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Composable
fun PetCard(pet: PetProfile) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(560.dp),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column {
            val painter = rememberAsyncImagePainter(model = pet.photoUri)

            Image(
                painter = painter,
                contentDescription = pet.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "${pet.name}, ${pet.age}",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )

                    Text(text = pet.type)
                }

                Spacer(modifier = Modifier.height(8.dp))
                Text(text = pet.breed)
            }
        }
    }
}

@Composable
fun ActionButtons(
    onNoClick: () -> Unit,
    onBookmarkClick: () -> Unit,
    onYesClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        CircleIconButton(
            imageVector = Icons.Default.Close,
            contentDescription = "No",
            onClick = onNoClick
        )

        CircleIconButton(
            imageVector = Icons.Default.Bookmark,
            contentDescription = "Save",
            onClick = onBookmarkClick
        )

        CircleIconButton(
            imageVector = Icons.Default.Favorite,
            contentDescription = "Yes",
            onClick = onYesClick
        )
    }
}

@Composable
fun CircleIconButton(
    imageVector: androidx.compose.ui.graphics.vector.ImageVector,
    contentDescription: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(64.dp)
            .background(Color.White, CircleShape)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}