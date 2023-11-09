package com.CyberDunkers.Sla7ly.data.repository

import com.CyberDunkers.Sla7ly.data.models.AuthVerifyBody
import com.CyberDunkers.Sla7ly.data.models.AuthVerifyResponse
import com.CyberDunkers.Sla7ly.data.models.AuthWithGoogleBody
import com.CyberDunkers.Sla7ly.data.models.PhoneRegistrationResponse
import com.CyberDunkers.Sla7ly.data.models.UpdateUserBody
import com.CyberDunkers.Sla7ly.data.remote.ClintApiInterface
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class ClintRepoImplTest{


    private lateinit var clintRepo: ClintRepoImpl
    private lateinit var clintApiInterface: ClintApiInterface

    @Before
    fun setUp() {
        clintApiInterface = mockk()
        clintRepo = ClintRepoImpl(clintApiInterface)
    }


    @Test
    fun userRegisteration() = runBlocking {
        //arrange
        val phone = "01019326050"
        val expectedResponse = PhoneRegistrationResponse(msg = true)
        coEvery { clintApiInterface.userRegisteration(phone) } returns expectedResponse

        //Act
        val result = clintRepo.userRegisteration(phone)

        // assert
        TestCase.assertEquals(expectedResponse, result)
    }

    @Test
    fun userVerify() = runBlocking {
        //arrange
        val body = AuthVerifyBody(phone = "01019326050", code = "1548")
        val expectedResponse = AuthVerifyResponse(
            msg = "old user",
            token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOi8vMTI3LjAuMC4xOjgwMDAvYXBpL3VzZXIvdmVyaWZ5IiwiaWF0IjoxNjk1Njk0NjAwLCJleHAiOjE2OTU2OTgyMDAsIm5iZiI6MTY5NTY5NDYwMCwianRpIjoic0lnSXJUbFAwb1kycGg0YyIsInN1YiI6IjMiLCJwcnYiOiIyM2JkNWM4OTQ5ZjYwMGFkYjM5ZTcwMWM0MDA4NzJkYjdhNTk3NmY3In0.WkSkr400aiEvHLaFolsBnddtzs3Ef-wONI6QCxUGBbU"

        )
        coEvery { clintApiInterface.userVerify(body) } returns expectedResponse

        // act
        val response = clintRepo.userVerify(body)

        // assert
        TestCase.assertEquals(expectedResponse, response)

    }

     fun updateUser() = runBlocking {
         // arrange
         val body = UpdateUserBody("01019326050" ,"Ahmed" , "farouk" ,"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOi8vMTI3LjAuMC4xOjgwMDAvYXBpL3VzZXIvdmVyaWZ5IiwiaWF0IjoxNjk1Njk0NjAwLCJleHAiOjE2OTU2OTgyMDAsIm5iZiI6MTY5NTY5NDYwMCwianRpIjoic0lnSXJUbFAwb1kycGg0YyIsInN1YiI6IjMiLCJwcnYiOiIyM2JkNWM4OTQ5ZjYwMGFkYjM5ZTcwMWM0MDA4NzJkYjdhNTk3NmY3In0.WkSkr400aiEvHLaFolsBnddtzs3Ef-wONI6QCxUGBbU")
         val exceptedResponse = PhoneRegistrationResponse(true)

         // act
         val response = clintRepo.updateUser(body)

         // assert
         TestCase.assertEquals(exceptedResponse , response)

     }



    @Test
    fun authWithGoogle() = runBlocking {
        //arrange
        val body = AuthWithGoogleBody(user_id = "236456" , user_name = "Ahmed Farouk")
        val expectedResponse = "created"
        val token = "sakfhskadhksdfljlsdjlasdfsdas4f5asd46fas6f6s"
        coEvery { clintApiInterface.authWithGoogle(body) } returns AuthVerifyResponse(expectedResponse , token = token )

        // act
        val response = clintRepo.authWithGoogle(body)

        // assert
        TestCase.assertEquals(expectedResponse, response)
    }
}