package com.example.lifepawtners.ui.swipe

// === IMPORTS ===
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
//import androidx.compose.ui.input.pointer.consume
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.example.lifepawtners.R
import com.example.lifepawtners.ui.theme.LifePawtnersTheme
import kotlin.math.roundToInt
import com.example.lifepawtners.ui.data.model.swipe.Pet
import androidx.compose.ui.draw.clip

@Composable
fun SwipeScreen(modifier: Modifier = Modifier) {

    // === SAMPLE PET DATA ===
    // Later this will come from a database
    val petList = remember {
        listOf(
            Pet(
                id = "pet1",
                ownerId = "account1",
                name = "Bella",
                age = 2,
                type = "Dog",
                breed = "Golden Retriever",
                bio = "Sweet and playful",
                imageRes = R.drawable.logo_words
            ),
            Pet(
                id = "pet2",
                ownerId = "account2",
                name = "Milo",
                age = 1,
                type = "Cat",
                breed = "Tabby",
                bio = "Curious and cuddly",
                imageRes = R.drawable.logo_words
            )
        )
    }

    // === STATE ===

    // Tracks which pet is currently shown
    var currentPetIndex by remember { mutableIntStateOf(0) }

    // Horizontal movement of the card
    var offsetX by remember { mutableFloatStateOf(0f) }

    // Smooth animation for movement
    val animatedOffsetX by animateFloatAsState(
        targetValue = offsetX,
        label = "card_offset"
    )

    // Slight rotation (makes it feel like Tinder)
    val rotation = animatedOffsetX / 60f

    // Move to next pet
    fun goToNextPet() {
        if (currentPetIndex < petList.lastIndex) {
            currentPetIndex++
        }
        offsetX = 0f
    }

    // === LAYOUT ===
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // === CARD AREA ===
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {

            if (currentPetIndex < petList.size) {

                val pet = petList[currentPetIndex]

                Box(
                    modifier = Modifier
                        .fillMaxWidth()

                        // Move card left/right
                        .offset {
                            IntOffset(animatedOffsetX.roundToInt(), 0)
                        }

                        // Rotate slightly while dragging
                        .graphicsLayer {
                            rotationZ = rotation
                        }

                        // Detect swipe gestures
                        .pointerInput(currentPetIndex) {
                            detectDragGestures(

                                onDrag = { change, dragAmount ->
                                    change.consume()
                                    offsetX += dragAmount.x
                                },

                                onDragEnd = {
                                    when {
                                        offsetX > 300f -> {
                                            // Swiped RIGHT (yes)
                                            goToNextPet()
                                        }
                                        offsetX < -300f -> {
                                            // Swiped LEFT (no)
                                            goToNextPet()
                                        }
                                        else -> {
                                            // Not far enough → reset
                                            offsetX = 0f
                                        }
                                    }
                                }
                            )
                        }
                ) {
                    PetCard(pet)
                }

            } else {
                Text("No more pets right now")
            }
        }

        // === BUTTONS ===
        ActionButtons(
            onNoClick = { goToNextPet() },
            onFavoriteClick = { /* future */ },
            onYesClick = { goToNextPet() }
        )
    }
}

@Composable
fun PetCard(pet: Pet) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(560.dp),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column {

            // === IMAGE ===
            Image(
                painter = painterResource(id = pet.imageRes),
                contentDescription = pet.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentScale = ContentScale.Crop
            )

            // === TEXT INFO ===
            Column(modifier = Modifier.padding(16.dp)) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        "${pet.name}, ${pet.age}",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )

                    Text(pet.type)
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(pet.breed)

                Spacer(modifier = Modifier.height(8.dp))

                Text(pet.bio)
            }
        }
    }
}

@Composable
fun ActionButtons(
    onNoClick: () -> Unit,
    onFavoriteClick: () -> Unit,
    onYesClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        CircleButton("X", onNoClick)
        CircleButton("♡", onFavoriteClick)
        CircleButton("✓", onYesClick)
    }
}

@Composable
fun CircleButton(
    label: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(64.dp)
            .clip(CircleShape)
            .background(Color.White)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SwipeScreenPreview() {
    LifePawtnersTheme {
        SwipeScreen()
    }
}