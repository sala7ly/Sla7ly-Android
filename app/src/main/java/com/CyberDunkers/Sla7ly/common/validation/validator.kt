package com.CyberDunkers.Sla7ly.common.validation

import android.text.TextUtils
import android.util.Patterns
import androidx.compose.runtime.MutableState

const val PHONE_LENGTH = 11
const val NATIONAL_ID_MAX_LENGTH = 14

enum class PassErrorType {
    EMPTY,
    LESS,
    NONE
}

data class PasswordError(
    var isError: Boolean = false,
    var type: PassErrorType = PassErrorType.NONE,
)

fun MutableState<String>.isValidEmail(): Boolean {
    return if (TextUtils.isEmpty(this.value)) false
    else Patterns.EMAIL_ADDRESS.matcher(this.value).matches()

}

fun MutableState<String>.isValidPhone(): Boolean {

    val phoneRegex = """^(\+20|0)(10|11|12|15)[0-9]{8,10}$""".toRegex()
    if ((this.value.length == 11) || (this.value.length==13 && this.value[0]=='+')){
        return phoneRegex.matches(this.value)
    }
    return false

}

fun MutableState<String>.isValidNationalID(): Boolean {
    val isNumeric = this.value.toBigDecimalOrNull() != null
    return this.value.length == NATIONAL_ID_MAX_LENGTH && this.value.isNotEmpty() && isNumeric
}

fun MutableState<String>.isValidPassword(): PasswordError {
    return if (this.value.isEmpty()) {
        PasswordError(isError = true, PassErrorType.EMPTY)
    } else if (this.value.length < 7) {
        PasswordError(isError = true, PassErrorType.LESS)
    } else {
        PasswordError(isError = false, PassErrorType.NONE)
    }
}

fun MutableState<String>.isValidPasswordConfirm(password1: MutableState<String>): Boolean {
    return password1.value == this.value
}

fun MutableState<String>.isValidName(): Boolean {
    return this.value.isNotEmpty()
}

