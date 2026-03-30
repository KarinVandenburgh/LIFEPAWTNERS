package com.example.lifepawtners.ui.pOwner

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState

@Composable
fun POwnerProfileSetupScreen(navController: NavController) {
    var firstName by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var housingType by remember { mutableStateOf("") }
    var bio by remember { mutableStateOf("") }

    var hasYard by remember { mutableStateOf("Yes") }
    var hasPets by remember { mutableStateOf("No") }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Set Up Your Profile",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Upload photo",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(12.dp))

            Box(
                modifier = Modifier
                    .size(width = 100.dp, height = 120.dp)
                    .border(
                        width = 1.5.dp,
                        color = Color.Gray,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .clickable {
                        // later: image picker
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "+",
                    style = MaterialTheme.typography.headlineMedium
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "First Name",
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(6.dp))

            OutlinedTextField(
                value = firstName,
                onValueChange = { firstName = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Enter first name") },
                singleLine = true,
                shape = RoundedCornerShape(14.dp)
            )

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = "Age",
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(6.dp))

            OutlinedTextField(
                value = age,
                onValueChange = { age = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Enter age") },
                singleLine = true,
                shape = RoundedCornerShape(14.dp)
            )

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = "Housing Type",
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(6.dp))

            OutlinedTextField(
                value = housingType,
                onValueChange = { housingType = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Apartment, house, condo...") },
                singleLine = true,
                shape = RoundedCornerShape(14.dp)
            )

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = "Do you have a yard?",
                fontWeight = FontWeight.Medium
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { hasYard = "Yes" }
                ) {
                    RadioButton(
                        selected = hasYard == "Yes",
                        onClick = { hasYard = "Yes" }
                    )
                    Text("Yes")
                }

                Spacer(modifier = Modifier.padding(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { hasYard = "No" }
                ) {
                    RadioButton(
                        selected = hasYard == "No",
                        onClick = { hasYard = "No" }
                    )
                    Text("No")
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = "Do you already have pets?",
                fontWeight = FontWeight.Medium
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { hasPets = "Yes" }
                ) {
                    RadioButton(
                        selected = hasPets == "Yes",
                        onClick = { hasPets = "Yes" }
                    )
                    Text("Yes")
                }

                Spacer(modifier = Modifier.padding(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { hasPets = "No" }
                ) {
                    RadioButton(
                        selected = hasPets == "No",
                        onClick = { hasPets = "No" }
                    )
                    Text("No")
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = "Bio",
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(6.dp))

            OutlinedTextField(
                value = bio,
                onValueChange = { bio = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                placeholder = { Text("Tell us a little about yourself") },
                shape = RoundedCornerShape(14.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    navController.navigate("main") {
                        popUpTo("login") { inclusive = true}
                    }

                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("Save Owner Profile")
            }
        }
    }
}