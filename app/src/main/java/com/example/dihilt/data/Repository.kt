package com.example.dihilt.data

import com.example.dihilt.ApiService
import javax.inject.Inject

// constructor injection at repository
class Repository @Inject constructor(private val apiService: ApiService) {
    fun getRemoteData() = apiService.getRandomDog()
}