#!/bin/bash

echo "Adding full app features..."

# More models
cat > app/src/main/java/com/makerledger/model/Project.kt << 'EOL'
package com.makerledger.model
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class Project(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val title: String,
    val hobby: HobbyType,
    val totalCost: Double,
    val sellingPrice: Double = 0.0,
    val date: Long = System.currentTimeMillis()
)
EOL

# Database
cat > app/src/main/java/com/makerledger/data/AppDatabase.kt << 'EOL'
package com.makerledger.data
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.makerledger.model.Material
import com.makerledger.model.Project

@Database(entities = [Material::class, Project::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun materialDao(): MaterialDao
    abstract fun projectDao(): ProjectDao
}
EOL

cat > app/src/main/java/com/makerledger/data/Converters.kt << 'EOL'
package com.makerledger.data
import androidx.room.TypeConverter
import com.makerledger.model.HobbyType

class Converters {
    @TypeConverter
    fun fromHobbyType(hobby: HobbyType): String = hobby.name

    @TypeConverter
    fun toHobbyType(name: String): HobbyType = HobbyType.valueOf(name)
}
EOL

# DAOs (basic)
cat > app/src/main/java/com/makerledger/data/MaterialDao.kt << 'EOL'
package com.makerledger.data
import androidx.room.*
import com.makerledger.model.Material
import kotlinx.coroutines.flow.Flow

@Dao
interface MaterialDao {
    @Query("SELECT * FROM material")
    fun getAll(): Flow<List<Material>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(material: Material)

    @Update
    suspend fun update(material: Material)
}
EOL

# JSON Export Utility
cat > app/src/main/java/com/makerledger/util/JsonExporter.kt << 'EOL'
package com.makerledger.util
import com.google.gson.Gson
import java.io.File

object JsonExporter {
    private val gson = Gson()

    fun exportMaterials(materials: List<Any>, file: File) {
        file.writeText(gson.toJson(materials))
    }

    fun exportProjects(projects: List<Any>, file: File) {
        file.writeText(gson.toJson(projects))
    }
}
EOL

# Enhanced Main Screen + Navigation
cat > app/src/main/java/com/makerledger/MainActivity.kt << 'EOL'
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
EOL

echo "✅ All core files added successfully!"
echo "Now open the project in Android Studio, Sync Gradle, and run it."
