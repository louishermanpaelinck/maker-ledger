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
