package com.makerledger.model
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class Material(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val hobby: HobbyType,
    val name: String,
    val unit: String,
    val pricePerUnit: Double,
    val currentStock: Double = 0.0,
    val minStock: Double = 5.0
)
