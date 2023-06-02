package com.godston.vfapplication.repository

import com.godston.vfapplication.api.CountryApi
import com.godston.vfapplication.utils.DataStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CountryRepository @Inject constructor(private val countryApi: CountryApi) {

    suspend fun getAllCountries() = flow {
        emit(DataStatus.loading())
        val result = countryApi.getAllCountries()
        when (result.code()) {
            200 -> {
                emit(DataStatus.success(result.body()))
            }
            400 -> {
                emit(DataStatus.error(result.message()))
            }
            500 -> {
                emit(DataStatus.error(result.message()))
            }
        }
    }.catch {
        emit(DataStatus.error(it.message.toString()))
    }.flowOn(Dispatchers.IO)
}
