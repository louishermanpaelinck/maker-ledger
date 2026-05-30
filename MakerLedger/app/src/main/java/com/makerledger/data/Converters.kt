package com.makerledger.data
import androidx.room.TypeConverter
import com.makerledger.model.HobbyType

class Converters {
    @TypeConverter
    fun fromHobbyType(hobby: HobbyType): String = hobby.name

    @TypeConverter
    fun toHobbyType(name: String): HobbyType = HobbyType.valueOf(name)
}
