package com.ferhatozcelik.codechallenge.ui.activitys

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.ferhatozcelik.codechallenge.data.local.UserSessionManager
import com.ferhatozcelik.codechallenge.navigation.NavGraph
import com.ferhatozcelik.codechallenge.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val sessionManager = remember { UserSessionManager(context = this) }
            MyApplicationTheme {
                val navController = rememberNavController()
                Surface(color = MaterialTheme.colorScheme.background) {
                    NavGraph(navController = navController, sessionManager)
                }
            }
        }
    }
}