package com.example.lifepawtners.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.HelpOutline
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.Rule
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


// IMPORTANT:
// This screen is shared by BOTH account types.
//
// 1. Potential Owner / Adopt a Pet
// 2. List a Pet / Shelter
//
// The difference between these two account types is already created earlier
// in your app flow:
//
// - CreateAuthActivity.kt chooses between "Adopt a Pet" and "List a Pet"
// - POwnerProfileSetupScreen.kt sets up the owner account
// - PetProfileSetupScreen.kt sets up the list-a-pet / shelter flow
//
// Because of that, this screen should stay as ONE AccountScreen,
// but it should conditionally show different options depending on the role.
//
// isPetLister = true  -> List a Pet / Shelter account
// isPetLister = false -> Potential Owner / Adopt a Pet account
//
// This keeps the UI consistent while still preserving different user experiences.

@Composable
fun AccountScreen(
    isPetLister: Boolean,
    onEditProfileClick: () -> Unit,
    onAddPetClick: () -> Unit,
    onManagePetsClick: () -> Unit,
    onLogoutClick: () -> Unit,
    onNotificationsClick: () -> Unit = {},
    onLocationClick: () -> Unit = {},
    onHelpClick: () -> Unit = {},
    onTermsClick: () -> Unit = {}
) {
    val backgroundColor = Color(0xFFF6F4F1)
    val cardColor = Color.White
    val accentColor = Color(0xFF4CAF7D)
    val textColor = Color(0xFF1E1E1E)
    val subtitleColor = Color(0xFF8B8B8B)
    val dividerColor = Color(0xFFF0F0F0)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .statusBarsPadding()
            .navigationBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        Text(
            text = "Account",
            style = MaterialTheme.typography.headlineSmall,
            color = textColor
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = if (isPetLister) "List a Pet Account" else "Potential Owner Account",
            style = MaterialTheme.typography.bodyMedium,
            color = subtitleColor
        )

        Spacer(modifier = Modifier.height(18.dp))

        Surface(
            shape = RoundedCornerShape(22.dp),
            color = cardColor,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                SettingsRow(
                    title = "Edit Profile",
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Edit Profile",
                            tint = accentColor
                        )
                    },
                    onClick = onEditProfileClick
                )

                // Only "List a Pet" / shelter accounts should see pet management actions.
                // This preserves the difference between the two signup/setup flows.
                if (isPetLister) {
                    HorizontalDivider(color = dividerColor)

                    SettingsRow(
                        title = "Add Pet",
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Pets,
                                contentDescription = "Add Pet",
                                tint = accentColor
                            )
                        },
                        onClick = onAddPetClick
                    )

                    HorizontalDivider(color = dividerColor)

                    SettingsRow(
                        title = "Manage Pets",
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Pets,
                                contentDescription = "Manage Pets",
                                tint = accentColor
                            )
                        },
                        onClick = onManagePetsClick
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(22.dp))

        Text(
            text = "Preferences",
            style = MaterialTheme.typography.titleMedium,
            color = textColor,
            modifier = Modifier.padding(start = 4.dp, bottom = 8.dp)
        )

        Surface(
            shape = RoundedCornerShape(22.dp),
            color = cardColor,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                SettingsRow(
                    title = "Notifications",
                    icon = {
                        Icon(
                            imageVector = Icons.Default.NotificationsNone,
                            contentDescription = "Notifications",
                            tint = accentColor
                        )
                    },
                    subtitle = "For show",
                    onClick = onNotificationsClick
                )

                HorizontalDivider(color = dividerColor)

                SettingsRow(
                    title = "Location",
                    icon = {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = "Location",
                            tint = accentColor
                        )
                    },
                    subtitle = "For show",
                    onClick = onLocationClick
                )
            }
        }

        Spacer(modifier = Modifier.height(22.dp))

        Text(
            text = "Support",
            style = MaterialTheme.typography.titleMedium,
            color = textColor,
            modifier = Modifier.padding(start = 4.dp, bottom = 8.dp)
        )

        Surface(
            shape = RoundedCornerShape(22.dp),
            color = cardColor,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                SettingsRow(
                    title = "Help",
                    icon = {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.HelpOutline,
                            contentDescription = "Help",
                            tint = accentColor
                        )
                    },
                    subtitle = "For show",
                    onClick = onHelpClick
                )

                HorizontalDivider(color = dividerColor)

                SettingsRow(
                    title = "Terms",
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Rule,
                            contentDescription = "Terms",
                            tint = accentColor
                        )
                    },
                    subtitle = "Later",
                    onClick = onTermsClick
                )
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        Surface(
            shape = RoundedCornerShape(22.dp),
            color = cardColor,
            modifier = Modifier.fillMaxWidth()
        ) {
            SettingsRow(
                title = "Logout",
                titleColor = Color(0xFFD9534F),
                icon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Logout,
                        contentDescription = "Logout",
                        tint = Color(0xFFD9534F)
                    )
                },
                onClick = onLogoutClick
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
fun SettingsRow(
    title: String,
    icon: @Composable () -> Unit,
    subtitle: String? = null,
    titleColor: Color = Color(0xFF1E1E1E),
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 18.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        icon()

        Spacer(modifier = Modifier.width(14.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                color = titleColor,
                style = MaterialTheme.typography.bodyLarge
            )

            if (subtitle != null) {
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = subtitle,
                    color = Color(0xFF8B8B8B),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }

        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            tint = Color(0xFFB0B0B0)
        )
    }
}
    @Composable
    fun ProfileTab(navController: NavController) {

    // TEMP: hardcoded role for now
        val isPetLister = true   // change to false to test owner

        AccountScreen(
            isPetLister = isPetLister,
            onEditProfileClick = { navController.navigate("edit_profile") },
            onAddPetClick = { navController.navigate("add_pet") },
            onManagePetsClick = { navController.navigate("manage_pets") },
            onLogoutClick = { navController.navigate("login") }
        )
    }