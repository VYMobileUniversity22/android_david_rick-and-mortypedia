package com.example.rickandmorty

import com.example.rickandmorty.data.api.CharactersService
import com.example.rickandmorty.data.model.CharactersDto
import com.example.rickandmorty.data.model.utils.getRetrofitInstance
import com.google.gson.GsonBuilder
import com.google.gson.internal.GsonBuildConfig
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.CoroutineContext

class MainPresenter(val mainView: Mvp.View) : Mvp.Presenter, CoroutineScope {
    private val job = Job()
    override val coroutineContext: CoroutineContext = job + Dispatchers.IO

    var greetings: String? = null
    private val retrofitInstance: Retrofit by lazy { getRetrofitInstance(converterFactory = GsonConverterFactory.create()) }
    private val characterService: CharactersService by lazy {
        retrofitInstance.create(
            CharactersService::class.java
        )
    }

    override fun onClickmeOptionSelected(num: Double) {
        greetings = anotherFun()
        if (num > 0.5) {
            mainView.showMessage()
        } else {
            mainView.showLogMessage()
        }

    }

    override fun onLaunchRequestOptionSelected() {
        launch {
            try {
                val response: CharactersDto? = characterService.getAllCharactersList()
                println(response?.toString())
            } catch (e: Exception) {
                println(e.printStackTrace())
            }
        }
    }

    private fun anotherFun(): String = "Hello"

}