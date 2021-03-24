package com.apyan.tastette.network

import com.apyan.tastette.data.RecipeListData
import retrofit2.Call
import retrofit2.Response

interface RecipeListCallback {
    fun onSuccessResponse(call: Call<RecipeListData.Result>, response: Response<RecipeListData.Result>)
    fun onFailureResponse(call: Call<RecipeListData.Result>, t: Throwable)
}