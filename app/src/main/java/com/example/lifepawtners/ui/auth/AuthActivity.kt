package com.example.lifepawtners.ui.auth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lifepawtners.R
import com.example.lifepawtners.ui.theme.LifePawtnersTheme
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lifepawtners.ui.navigation.AppNavigation
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import com.example.lifepawtners.ui.data.local.DatabaseProvider


class AuthActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LifePawtnersTheme {
                AppNavigation()
                /*val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginScreen(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding))
                }*/
            }
        }
    }
}

@Composable
fun LoginScreen(
    navController: NavController,
    modifier: Modifier = Modifier) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var emailError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }
    var loginError by remember { mutableStateOf("") }

    val context = LocalContext.current
    val db = DatabaseProvider.getDatabase(context)
    val userDao = db.userAccountDao()
    val scope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(painter = painterResource(id = R.drawable.pawtnerslogo) , contentDescription = "Logo",
        modifier = Modifier.size(200.dp))

        Text(
            text = "Welcome to LifePawtners!",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                emailError = ""
                loginError = ""
            },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            isError = emailError.isNotEmpty()
        )

        if (emailError.isNotEmpty()) {
            Text(
                text = emailError,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                passwordError = ""
                loginError = ""
            },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            isError = passwordError.isNotEmpty()
        )

        if (passwordError.isNotEmpty()) {
            Text(
                text = passwordError,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
        if (loginError.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = loginError,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Button(
            onClick = {
                emailError = ""
                passwordError = ""
                loginError = ""

                when {
                    email.isBlank() -> {
                        emailError = "Email is required"
                    }

                    !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                        emailError = "Enter a valid email address"
                    }

                    password.isBlank() -> {
                        passwordError = "Password is required"
                    }

                    else -> {
                        scope.launch {
                            val existingUser = userDao.getUserByEmail(email)

                            if (existingUser == null) {
                                loginError = "Account info not found"
                            } else {
                                val loggedInUser = userDao.login(email, password)
                                val cleanEmail = email.trim().replace(".","_")

                                if (loggedInUser == null) {
                                    loginError = "Incorrect password"
                                } else {
                                    if (loggedInUser.role == "ADOPTER") {
                                        navController.navigate("main/${cleanEmail}") {
                                            popUpTo("login") { inclusive = true }
                                        }
                                    } else {
                                        navController.navigate("requests/${cleanEmail}") {
                                            popUpTo("login") { inclusive = true }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium
        ) {
            Text("Login")
        }
        Spacer(modifier = Modifier.height(10.dp))
        }
        Button(
            onClick = { navController.navigate("create") },
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium
        ) {
            Text("Create an account")
        }
    }


@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    LifePawtnersTheme {
        val navController = rememberNavController()
        LoginScreen(navController)

    }
}
