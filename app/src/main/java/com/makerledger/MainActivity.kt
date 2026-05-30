package com.makerledger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.makerledger.model.HobbyType
import com.makerledger.ui.theme.MakerLedgerTheme
import com.makerledger.ui.theme.GlassCard

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MakerLedgerTheme {
                MakerLedgerApp()
            }
        }
    }
}

@Composable
fun MakerLedgerApp() {
    var currentScreen by remember { mutableStateOf("home") }
    var selectedHobby by remember { mutableStateOf(HobbyType.THREE_D_PRINTING) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Maker Ledger", style = MaterialTheme.typography.headlineLarge)

        Spacer(Modifier.height(16.dp))

        // Hobby Selector
        GlassCard {
            Column(Modifier.padding(16.dp)) {
                Text("Select Hobby", style = MaterialTheme.typography.titleMedium)
                // Simple list for now
                HobbyType.entries.forEach { hobby ->
                    Button(onClick = { selectedHobby = hobby; currentScreen = "calculator" }) {
                        Text(hobby.displayName)
                    }
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        when (currentScreen) {
            "calculator" -> CostCalculatorScreen(selectedHobby)
            else -> HomeDashboard()
        }
    }
}

@Composable
fun HomeDashboard() {
    GlassCard(modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.padding(20.dp)) {
            Text("Welcome to Maker Ledger", style = MaterialTheme.typography.titleLarge)
            Text("Track your hobbies • Calculate costs • Manage stock")
        }
    }
}

@Composable
fun CostCalculatorScreen(hobby: HobbyType) {
    GlassCard(modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.padding(20.dp)) {
            Text("Cost Calculator - ${hobby.displayName}", style = MaterialTheme.typography.titleMedium)
            Text("Full calculator UI coming in next update...")
        }
    }
}
