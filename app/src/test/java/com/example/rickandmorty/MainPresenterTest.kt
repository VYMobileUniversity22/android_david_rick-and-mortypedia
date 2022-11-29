package com.example.rickandmorty

import com.example.rickandmorty.character.domain.CharactersDomainLayerContract
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

/*
class MainPresenterTest() {

    //Subject Under Test
    private lateinit var sut: MainPresenter

    @Before
    fun setUp() {
        sut = MainPresenter(mock(Mvp.View::class.java),
            getAllCharactersUc = mock(CharactersDomainLayerContract.PresentationLayer.UseCase::class.java) )
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `When 'click me' option is selected, and 'num' less than 0_5 ,'showMessage' is invoked`(){
        //given
        val num: Double = 0.5
        //when
        sut.onClickmeOptionSelected(num = num)
        //then
        verify(sut.mainView).showMessage()
    }

    @Test
    fun `When 'click me' option is selected, 'greetings' is assigned 'Hello'`() {
        //When
        sut.onClickmeOptionSelected(3.0)
        //then
        assert(sut.greetings == "Hello")
    }



}*/
