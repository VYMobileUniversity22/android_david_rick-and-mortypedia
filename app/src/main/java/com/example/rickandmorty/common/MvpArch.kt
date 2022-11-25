package com.example.rickandmorty

import com.example.rickandmorty.character.domain.model.Characters

interface Mvp {

    interface View {
        fun showMessage()
        fun showLogMessage() {

        }

        fun loadCharacters(data: Characters)
        fun showErrorMessage(msg: String)
    }

    interface Presenter {
        fun onClickmeOptionSelected(num: Double)
        fun onLaunchRequestOptionSelected()
        fun onLaunchSeveralRequestOptionSelected()
        fun onViewPaused()
    }

}