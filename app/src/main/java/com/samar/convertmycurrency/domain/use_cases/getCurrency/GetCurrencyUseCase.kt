package com.samar.convertmycurrency.domain.use_cases.getCurrency

import com.samar.convertmycurrency.data.db.entity.CurrencyType
import com.samar.convertmycurrency.data.remote.dto.CurrencyDto
import com.samar.convertmycurrency.domain.repository.CurrencyRepository
import com.samar.convertmycurrency.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCurrencyUseCase
@Inject constructor(private val repository: CurrencyRepository) {

    operator fun invoke(): Flow<Resource<CurrencyDto>> = flow {
        try {
            emit(Resource.Loading())
            val currencyLive = repository.getAllListCurrencyList()
            if(!currencyLive.isNullOrEmpty()){
                emit(Resource.Success(currencyLive))
            }
            else{
                emit(Resource.Error("Something is wring"))
            }

        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    e.localizedMessage ?: "Couldn't reach server. Check your internet connection."
                )
            )
        }
    }



    fun convertCurrency(from: String, to : String, amount: String): Flow<Resource<Float>> = flow {
        try {
            emit(Resource.Loading())
            val convertedCurrency = repository.convertCurrency(from, to, amount)
            if(convertedCurrency!=null){
                emit(Resource.Success(convertedCurrency))
            }
            else{
                emit(Resource.Error("Something is wring"))
            }

        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    e.localizedMessage ?: "Couldn't reach server. Check your internet connection."
                )
            )
        }
    }

    suspend fun insertCurrenciesToDb(currencies: ArrayList<CurrencyType>){
       repository.insertAllListCurrencyListToDb(currencies)
    }
}