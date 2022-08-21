package com.samar.convertmycurrency.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.samar.convertmycurrency.data.db.entity.CurrencyType

@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrencyType(currencyTypes: ArrayList<CurrencyType>)

    @Query("SELECT * FROM currencyType ORDER BY ID DESC")
    fun getCurrencyType(): LiveData<List<CurrencyType>>



}