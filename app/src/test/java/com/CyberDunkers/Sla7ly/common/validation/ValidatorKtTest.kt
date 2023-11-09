package com.CyberDunkers.Sla7ly.common.validation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ValidatorKtTest{


    // bad scenarios
    @Test
    fun `bad scenario-1 empty string`(){
        val phone : MutableState<String> = mutableStateOf("")
        val res = phone.isValidPhone()
        assertThat(res).isFalse()
    }
    @Test
    fun `bad scenario-2 less than 11 num`(){
        val phone = mutableStateOf("0101932605")
        val res = phone.isValidPhone()
        assertThat(res).isFalse()
    }

    @Test
    fun `bad scenario-4 not started with 01`(){
        val phone = mutableStateOf("10019326050")
        val res = phone.isValidPhone()
        assertThat(res).isFalse()
    }
    @Test
    fun `bad scenario-5 more than 11 number and without +20`(){
        val phone = mutableStateOf("010193260500")
        val res = phone.isValidPhone()
        assertThat(res).isFalse()
    }

    // good scenarios
    @Test
    fun `good scenario-1 phone is valid`(){
        val phone = mutableStateOf("+201019326050")
        val res = phone.isValidPhone()
        assertThat(res).isTrue()
    }

    @Test
    fun `good scenario-2 phone is valid`(){
        val phone = mutableStateOf("+201519326050")
        val res = phone.isValidPhone()
        assertThat(res).isTrue()
    }
    @Test
    fun `good scenario-3 phone is valid`(){
        val phone = mutableStateOf("01519326050")
        val res = phone.isValidPhone()
        assertThat(res).isTrue()
    }


    // national id

    // bad scenario
    @Test
    fun `national id is empty`(){
        val nationalId = mutableStateOf("")
        val res = nationalId.isValidNationalID()
        assertThat(res).isFalse()
    }
    @Test
    fun `national id is less than 14`(){
        val nationalId = mutableStateOf("12345678910")
        val res = nationalId.isValidNationalID()
        assertThat(res).isFalse()
    }
    @Test
    fun `national id is more than 14`(){
        val nationalId = mutableStateOf("123456789123456")
        val res = nationalId.isValidNationalID()
        assertThat(res).isFalse()
    }


    // good scenario
    @Test
    fun `numeric with size equal to 14 digits`(){
        val nationalId = mutableStateOf("12345678912345")
        val res = nationalId.isValidNationalID()
        assertThat(res).isTrue()
    }


}