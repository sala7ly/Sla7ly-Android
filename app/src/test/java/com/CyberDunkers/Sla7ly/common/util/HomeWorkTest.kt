package com.CyberDunkers.Sla7ly.common.util

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class HomeWorkTest{

    @Test
    fun `reverse of the given number != number`(){
        val res = HomeWork.isPalindrome(123)
        assertThat(res).isFalse()
    }
    @Test
    fun `reverse of the given number = number`(){
        val res = HomeWork.isPalindrome(121)
        assertThat(res).isTrue()
    }


    // with one right / left
    //with more than write or left
    @Test
    fun `string without curly brackets on the left`() {
        val result =  HomeWork.checkBraces("(string")
        assertThat(result).isFalse()
    }

    @Test
    fun `string without curly brackets on the right`() {
        val result =  HomeWork.checkBraces("string)")
        assertThat(result).isFalse()
    }

    @Test
    fun `string with 2 curly brackets on the right`() {
        val result =  HomeWork.checkBraces("string))")
        assertThat(result).isFalse()
    }

    @Test
    fun `string with 2 curly brackets on the left`() {
        val result =  HomeWork.checkBraces("((string")
        assertThat(result).isFalse()
    }

    @Test
    fun `string with curly brackets`() {
        val result =  HomeWork.checkBraces("(string)")
        assertThat(result).isTrue()
    }

    @Test
    fun `string without curly brackets`() {
        val result =  HomeWork.checkBraces("string")
        assertThat(result).isTrue()
    }


}
