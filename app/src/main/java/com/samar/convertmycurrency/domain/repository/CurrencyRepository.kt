package com.samar.convertmycurrency.domain.repository

import androidx.lifecycle.LiveData
import com.samar.convertmycurrency.data.db.entity.CurrencyType
import com.samar.convertmycurrency.data.remote.dto.CurrencyDto

interface CurrencyRepository {

    suspend fun getAllListCurrencyList(): CurrencyDto?

    suspend fun getAllListCurrencyListFromDb(): LiveData<List<CurrencyType>>

    suspend fun insertAllListCurrencyListToDb(currencies: ArrayList<CurrencyType>)

    suspend fun convertCurrency(
        from: String,
        to: String,
        q: String
    ): Float?
}