package com.CyberDunkers.Sla7ly.common.util

object HomeWork {

    /**
     * Checks if the braces are set correctly
     * e.g. "(a * b))" should return false
     */

    fun checkBraces(string: String): Boolean {
        return string.count { it == '(' } == string.count { it == ')' }
    }


    /**
    * check if the reverse of the number equal to the number
    * */
    fun isPalindrome(x: Int): Boolean {
      val num = x.toString()
        val revNum = num.reversed()
        return num==revNum
    }


}