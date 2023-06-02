package com.godston.vfapplication.api

import com.godston.vfapplication.model.Country
import retrofit2.Response
import retrofit2.http.GET

interface CountryApi {

    @GET("atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json")
    suspend fun getAllCountries(): Response<List<Country>>
}