package com.samar.convertmycurrency.data.repository

import androidx.lifecycle.LiveData
import com.samar.convertmycurrency.data.db.dao.CurrencyDao
import com.samar.convertmycurrency.data.db.entity.CurrencyType
import com.samar.convertmycurrency.data.remote.CurrencyApi
import com.samar.convertmycurrency.data.remote.dto.CurrencyDto
import com.samar.convertmycurrency.domain.repository.CurrencyRepository

class CurrencyRepositoryImp
constructor(
    private val currencyApi: CurrencyApi,
    private val currencyDao: CurrencyDao
) : CurrencyRepository {

    override suspend fun getAllListCurrencyList(): CurrencyDto {
        return currencyApi.getAllListCurrencyList()
    }

    override suspend fun getAllListCurrencyListFromDb(): LiveData<List<CurrencyType>> {
        return currencyDao.getCurrencyType()
    }

    override suspend fun insertAllListCurrencyListToDb(currencies: ArrayList<CurrencyType>) {
        currencyDao.insertCurrencyType(currencies)
    }

    override suspend fun convertCurrency(from: String, to: String, q: String): Float {
        return currencyApi.convertCurrency(from, to, q)
    }

}