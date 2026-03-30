package com.example.lifepawtners.ui.auth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lifepawtners.ui.theme.LifePawtnersTheme

class CreateAuthActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LifePawtnersTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CreateScreen(
                        navController = rememberNavController(),
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun RoleSelectionToggle(
    isAdoptP: Boolean,
    onRoleSelected: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor = MaterialTheme.colorScheme.primary
    val selectedColor = Color.White
    val unselectedContentColor = Color.White
    val selectedContentColor = MaterialTheme.colorScheme.primary

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = CircleShape,
        color = backgroundColor
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(4.dp)
                    .clip(CircleShape)
                    .background(if (isAdoptP) selectedColor else Color.Transparent)
                    .clickable { onRoleSelected(true) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Adopt a Pet",
                    color = if (isAdoptP) selectedContentColor else unselectedContentColor,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }

            Box(
                modifier = modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(4.dp)
                    .clip(CircleShape)
                    .background(if (!isAdoptP) selectedColor else Color.Transparent)
                    .clickable { onRoleSelected(false) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "List a Pet",
                    color = if (!isAdoptP) selectedContentColor else unselectedContentColor,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
        }
    }
}

@Composable
fun CreateScreen(
    navController: NavController,
    modifier: Modifier = Modifier) {
    var fname by remember { mutableStateOf("") }
    var lname by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isAdoptP by remember { mutableStateOf(true) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        IconButton(
            onClick = { navController.navigate("login") },
            modifier = Modifier.align(Alignment.TopStart)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = MaterialTheme.colorScheme.primary
            )

        }
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Sign up to find your LifePartner!",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        
        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = fname,
            onValueChange = { fname = it },
            label = { Text("First Name") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = lname,
            onValueChange = { lname = it },
            label = { Text("Last Name") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "I am looking to:",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(8.dp))

        RoleSelectionToggle(
            isAdoptP = isAdoptP,
            onRoleSelected = { isAdoptP = it }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (isAdoptP) {
                    navController.navigate("owner_profile_setup")
                } else {
                    navController.navigate("pet_profile_setup")
                }
            }
        ) {
            Text("Sign Up")
        }

    }
}

@Preview(showBackground = true)
@Composable
fun CreatePreview() {
    LifePawtnersTheme {
        CreateScreen(
            navController = rememberNavController()
        )
    }
}
