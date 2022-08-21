package com.samar.convertmycurrency.data.remote

import com.samar.convertmycurrency.data.remote.dto.CurrencyDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApi {

    @GET("listquotes")
    suspend fun getAllListCurrencyList(): CurrencyDto

    @GET("exchange")
    suspend fun convertCurrency(
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("q") q: String
    ): Float

}