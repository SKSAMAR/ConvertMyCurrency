package com.samar.convertmycurrency.di

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.samar.convertmycurrency.R
import com.samar.convertmycurrency.data.db.CurrencyDatabase
import com.samar.convertmycurrency.data.db.dao.CurrencyDao
import com.samar.convertmycurrency.data.db.entity.CurrencyType
import com.samar.convertmycurrency.data.remote.CurrencyApi
import com.samar.convertmycurrency.data.repository.CurrencyRepositoryImp
import com.samar.convertmycurrency.domain.repository.CurrencyRepository
import com.samar.convertmycurrency.util.Constant.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }


    @Singleton
    @Provides
    fun getOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        @ApplicationContext context: Context
    ): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
            .callTimeout(5, TimeUnit.MINUTES)
            .readTimeout(5, TimeUnit.MINUTES)
            .connectTimeout(5, TimeUnit.MINUTES)
            .readTimeout(5, TimeUnit.MINUTES)
            .writeTimeout(5, TimeUnit.MINUTES)
            .addInterceptor(httpLoggingInterceptor)
        httpClient.addInterceptor(Interceptor { chain: Interceptor.Chain ->
            val request: Request = chain.request().newBuilder()
                .addHeader("X-RapidAPI-Host", context.resources.getString(R.string.Host))
                .addHeader("X-RapidAPI-Key", context.resources.getString(R.string.Key))
                .build()
            val response: Response = chain.proceed(request)
            response
        })

        return httpClient.build()
    }


    @Singleton
    @Provides
    fun getCurrencyApi(httpClient: OkHttpClient): CurrencyApi {
        val gson = GsonBuilder()
            .setLenient()
            .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
            .create()
        val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BASE_URL)
            .client(httpClient)
            .build()
        return retrofit.create(CurrencyApi::class.java)
    }


    @Singleton
    @Provides
    fun getMovieRepository(movieApi: CurrencyApi, currencyDao: CurrencyDao): CurrencyRepository {
        return CurrencyRepositoryImp(movieApi, currencyDao)
    }

    @Singleton
    @Provides
    fun getCurrencyDatabase(@ApplicationContext context: Context): CurrencyDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            CurrencyDatabase::class.java,
            "currencyContentDb"
        ).allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()


    @Singleton
    @Provides
    fun getCurrencyDao(currencyDatabase: CurrencyDatabase): CurrencyDao = currencyDatabase.currencyDao()

    @Singleton
    @Provides
    fun getCurrencyList(currencyDao: CurrencyDao): LiveData<List<CurrencyType>> = currencyDao.getCurrencyType()
}