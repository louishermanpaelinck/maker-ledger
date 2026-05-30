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
