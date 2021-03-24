package com.apyan.tastette.network

import com.apyan.tastette.data.RecipeListData
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {

    private val client = OkHttpClient.Builder().build()

    private val retrofit = Retrofit.Builder()
            .baseUrl("https://api.duckduckgo.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    fun<T> buildService(service: Class<T>): T{
        return retrofit.create(service)
    }

    fun startCharacterListDownload(characterDownloadInterface: RecipeListCallback,
                                   call : Call<RecipeListData.Result>) {
        call.enqueue(object : Callback<RecipeListData.Result> {
            override fun onResponse(call: Call<RecipeListData.Result>, response: Response<RecipeListData.Result>) {
                if (response.isSuccessful){
                    RecipeListData.mTopicsDownloadedList = response.body()!!.RelatedTopics!!
                    characterDownloadInterface.onSuccessResponse(call, response)
                }
            }
            override fun onFailure(call: Call<RecipeListData.Result>, t: Throwable) {
                characterDownloadInterface.onFailureResponse(call, t)
            }
        })
    }
}