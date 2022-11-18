package com.example.rickandmorty

import com.example.rickandmorty.data.api.CharactersService
import com.example.rickandmorty.data.model.CharactersDto
import com.example.rickandmorty.data.model.utils.getRetrofitInstance
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.CoroutineContext

class MainPresenter(val mainView: Mvp.View) : Mvp.Presenter, CoroutineScope {
    override val coroutineContext: CoroutineContext = Job() + Dispatchers.Main
    private var job: Job? = null

    var greetings: String? = null
    private val retrofitInstance: Retrofit by lazy { getRetrofitInstance(converterFactory = GsonConverterFactory.create()) }
    private val characterService: CharactersService by lazy {
        retrofitInstance.create(CharactersService::class.java)
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
        job = launch {
            try {
                val response: CharactersDto? = characterService.getAllCharactersList()
                println(response?.toString())
            } catch (e: Exception) {
                println(e.printStackTrace())
            }
        }
    }

    override fun onLaunchSeveralRequestOptionSelected() {
        val a: Deferred<CharactersDto?> = async(Dispatchers.IO) {
            println(Thread.currentThread().name)
            characterService.getAllCharactersList()
        }
        val b: Deferred<CharactersDto?> = async(Dispatchers.IO) {
            println(Thread.currentThread().name)
            characterService.getAllCharactersList()
        }
        launch {
            println(Thread.currentThread().name)
            println(a.await()?.info?.count?.plus(b.await()?.info?.pages ?: 0))
        }
    }

    override fun onViewPaused() {
        job?.cancel()
    }

    private fun anotherFun(): String = "Hello"
}


