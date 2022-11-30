package com.example.rickandmorty.login.domain.usecase

import com.example.rickandmorty.login.data.repository.RickAndMortyLoginRepository
import com.example.rickandmorty.login.domain.LoginDomainLayerContract
import com.example.rickandmorty.login.domain.model.Email
import com.example.rickandmorty.login.domain.model.Keypass
import com.example.rickandmorty.login.domain.model.LoginUser
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class LoginUserWithEmailAndKeypassUcTest {

    private lateinit var sut: LoginDomainLayerContract.PresentationLayer.UseCase<LoginUser>
    private lateinit var mockLoginRepository: LoginDomainLayerContract.DataLayer.LoginRepository

    @Before
    fun setUp() {
        mockLoginRepository = Mockito.mock(LoginDomainLayerContract.DataLayer.LoginRepository::class.java)
        sut = LoginUserWithEmailAndKeypassUc(loginRepository = mockLoginRepository)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `When 'Email' and 'Keypass' are valid, 'loginUser' is invoked`() = runTest {
        //Given
        val validEmail = Email(value = "whatever@anything.com")
        val validKeypass = Keypass(value = "abcdefgh")
        //When
        sut(email = validEmail, keypass = validKeypass)
        //Then
        Mockito.verify(mockLoginRepository).loginUser(email = validEmail, keypass = validKeypass)
    }

}