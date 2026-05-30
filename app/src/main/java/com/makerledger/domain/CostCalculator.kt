package com.makerledger.domain
import com.makerledger.model.HobbyType
import com.makerledger.model.Material

class CostCalculator {
    fun calculateCost(hobby: HobbyType, material: Material, amountUsed: Double,
        hoursUsed: Double = 0.0, powerWatts: Double = 0.0, electricityRate: Double = 0.28): Double {
        val materialCost = amountUsed * material.pricePerUnit
        val electricityCost = (hoursUsed * powerWatts / 1000.0) * electricityRate
        val extraCost = when (hobby) {
            HobbyType.NAIL_ART -> hoursUsed * 1.5
            HobbyType.CANDLE_MAKING, HobbyType.SOAP_MAKING -> amountUsed * 0.1
            HobbyType.THREE_D_PRINTING -> hoursUsed * 0.8
            else -> 0.0
        }
        return materialCost + electricityCost + extraCost
    }
}
