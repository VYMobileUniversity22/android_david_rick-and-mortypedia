package com.example.rickandmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.rickandmorty.character.data.di.CharactersDataModule
import com.example.rickandmorty.character.presentation.di.CharactersPresentationModule
import retrofit2.Retrofit
import javax.inject.Inject

class MainActivity : AppCompatActivity(), Mvp.View {

    @Inject
    lateinit var mainPresenter: Mvp.Presenter



    override fun onCreate(savedInstanceState: Bundle?) {
        //(applicationContext as RickAndMortyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        (application as RickAndMortyApplication).provideCharactersComponentFactory()
            .create(
                presentationModule = CharactersPresentationModule(this),
                dataModule = CharactersDataModule(this)
                ).inject(this)
        setContentView(R.layout.activity_main)
        initViews()
    }

    override fun showMessage() {
        Retrofit.Builder()
        Toast.makeText(this,"Button clicked!", Toast.LENGTH_SHORT).show()
    }


    private fun initViews() {
        val button: Button = findViewById(R.id.button)
        button.setOnClickListener { mainPresenter.onClickmeOptionSelected(num = Math.random()) }

        val buttonRequest: Button = findViewById(R.id.button_coroutines)
        buttonRequest.setOnClickListener { mainPresenter.onLaunchRequestOptionSelected() }

        findViewById<Button>(R.id.button_parallel_coroutines).apply {
            setOnClickListener { mainPresenter.onLaunchSeveralRequestOptionSelected() }
        }
    }

}