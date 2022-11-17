package com.example.rickandmorty

import com.example.rickandmorty.data.api.CharactersService
import com.example.rickandmorty.data.model.CharactersDto
import com.example.rickandmorty.data.model.utils.getRetrofitInstance
import com.google.gson.GsonBuilder
import com.google.gson.internal.GsonBuildConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory

class MainPresenter(val mainView: Mvp.View): Mvp.Presenter {
    var greetings:String? = null

    override fun onClickmeOptionSelected(num: Double) {
        greetings = anotherFun()
        if (num > 0.5) {
            mainView.showMessage()
        }else{
            mainView.showLogMessage()
        }
        val charactersCall: Call<CharactersDto?> = getRetrofitInstance(converterFactory = GsonConverterFactory.create())
            .create(CharactersService::class.java)
            .getAllCharactersList()

        charactersCall.enqueue(object : Callback<CharactersDto?> {
            override fun onResponse(
                call: Call<CharactersDto?>,
                response: Response<CharactersDto?>
            ) {
                println(response.body()?.toString())
            }

            override fun onFailure(call: Call<CharactersDto?>, t: Throwable) {
                println("Oh no!")
            }

        })
    }

    private fun anotherFun(): String = "Hello"
}