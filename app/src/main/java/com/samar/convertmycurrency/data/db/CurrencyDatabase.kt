package com.samar.convertmycurrency.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.samar.convertmycurrency.data.db.dao.CurrencyDao
import com.samar.convertmycurrency.data.db.entity.CurrencyType

@Database(entities = [CurrencyType::class], version = 2, exportSchema = false)
abstract class CurrencyDatabase : RoomDatabase() {

    abstract fun currencyDao(): CurrencyDao
}