package com.example.dihilt

import com.example.dihilt.Constants.END_POINT
import io.reactivex.Single
import retrofit2.http.GET
//API service interface for API method
interface ApiService {

    @GET(END_POINT)
    fun getRandomDog(): Single<ApiResponse>
}