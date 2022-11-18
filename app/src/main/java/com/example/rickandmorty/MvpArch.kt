package com.example.rickandmorty

interface Mvp {

    interface View {
        fun showMessage()
        fun showLogMessage() {

        }
    }

    interface Presenter {
        fun onClickmeOptionSelected(num: Double)
        fun onLaunchRequestOptionSelected()
        fun onLaunchSeveralRequestOptionSelected()
        fun onViewPaused()
    }

}