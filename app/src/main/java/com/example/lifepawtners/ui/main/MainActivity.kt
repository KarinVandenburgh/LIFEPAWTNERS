package com.example.lifepawtners.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.example.lifepawtners.ui.theme.LifePawtnersTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LifePawtnersTheme {
                //calls main screen
                MainScreen()
            }
        }
    }
}
