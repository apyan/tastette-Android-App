package com.apyan.tastette.network

import com.apyan.tastette.data.RecipeListData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceNetworkCalls {

    @GET("/")
    fun getCharacterList(
            @Query("q") query: String,
            @Query("format") format: String
    ): Call<RecipeListData.Result>
}