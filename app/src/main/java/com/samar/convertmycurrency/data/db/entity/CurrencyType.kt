package com.samar.convertmycurrency.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currencyType")
data class CurrencyType(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val currencyType: String
)
