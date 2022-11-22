package com.example.rickandmorty

import com.example.rickandmorty.character.domain.DomainLayerContract
import com.example.rickandmorty.character.domain.model.Characters
import com.example.rickandmorty.character.domain.usecase.GetAllCharactersUc
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class MainPresenter @Inject constructor(
    val mainView: Mvp.View,
    //Forma habitual, en el constructor en classes con constructor
    //private val getAllCharactersUc: DomainLayerContract.PresentationLayer.UseCase
) : Mvp.Presenter, CoroutineScope {

    //Forma no habitual de inyectar dependencias, donde no hay constructor (Activity)
    //@Inject
    //lateinit var uc: GetAllCharactersUc

    override val coroutineContext: CoroutineContext = Job() + Dispatchers.Main
    private var job: Job? = null
    var greetings: String? = null

    //private val retrofitInstance: Retrofit by lazy { getRetrofitInstance(converterFactory = GsonConverterFactory.create()) }
    //private val characterService: CharactersService by lazy { retrofitInstance.create(CharactersService::class.java)}
    private val getAllCharactersUc: DomainLayerContract.PresentationLayer.UseCase by lazy { GetAllCharactersUc() }
    /*init {
        (appComponent as RickAndMortyApplication).inject(this)
    }*/


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
                val characters: Characters = getAllCharactersUc.getAllCharacters()
                //val response: CharactersDto? = characterService.getAllCharactersList()
                println(characters.toString())
            } catch (e: Exception) {
                println(e.printStackTrace())
            }
        }
    }

    override fun onLaunchSeveralRequestOptionSelected() {
        //  val a: Deferred<CharactersDto?> = async(Dispatchers.IO) {
        //      println(Thread.currentThread().name)
        //      characterService.getAllCharactersList()
        //  }
        //  val b: Deferred<CharactersDto?> = async(Dispatchers.IO) {
        //      println(Thread.currentThread().name)
        //      characterService.getAllCharactersList()
        //  }
        //  launch {
        //      println(Thread.currentThread().name)
        //      println(a.await()?.info?.count?.plus(b.await()?.info?.pages ?: 0))
        //  }
    }

    override fun onViewPaused() {
        job?.cancel()
    }

    private fun anotherFun(): String = "Hello"
}


