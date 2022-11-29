package com.example.rickandmorty.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.Mvp
import com.example.rickandmorty.R
import com.example.rickandmorty.RickAndMortyApplication
import com.example.rickandmorty.character.data.di.CharactersDataModule
import com.example.rickandmorty.character.di.CharactersComponent
import com.example.rickandmorty.character.domain.model.Characters
import com.example.rickandmorty.character.presentation.di.CharactersPresentationModule
import com.example.rickandmorty.character.presentation.view.CharactersAdapter
import retrofit2.Retrofit
import javax.inject.Inject

class MainActivity : AppCompatActivity(), Mvp.View {

    @Inject
    lateinit var mainPresenter: Mvp.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        getCharactersComponent().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        mainPresenter.onViewCreated()
    }

    override fun onPause() {
        super.onPause()
        mainPresenter.onViewPaused()
    }

    override fun showMessage() {
        Retrofit.Builder()
        Toast.makeText(this, "Button clicked!", Toast.LENGTH_SHORT).show()
    }

    override fun loadCharacters(data: Characters) {
        val recyclerView: RecyclerView = findViewById(R.id.rv_characters_data)
        recyclerView.layoutManager = LinearLayoutManager (this, RecyclerView.VERTICAL, false)
        val adapter = CharactersAdapter(data = (data.results).toMutableList())
        recyclerView.adapter = adapter
    }

    override fun showErrorMessage(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }


    /*private fun initViews() {

        val buttonRequest: Button = findViewById(R.id.button_coroutines)
        buttonRequest.setOnClickListener { mainPresenter.onLaunchRequestOptionSelected() }

    }*/

    private fun MainActivity.getCharactersComponent(): CharactersComponent =
        (application as RickAndMortyApplication).provideCharactersComponentFactory()
            .create(
                presentationModule = CharactersPresentationModule(this),
                dataModule = CharactersDataModule
            )
}