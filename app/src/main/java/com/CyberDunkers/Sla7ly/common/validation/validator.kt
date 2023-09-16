package com.CyberDunkers.Sla7ly.common.validation

import android.text.TextUtils
import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import com.CyberDunkers.Sla7ly.presentation.authentication.clint.EditeTextLabels

const val PHONE_MAX_LENGTH = 11
const val NATIONAL_ID_MAX_LENGTH = 14

data class PasswordError(
    var isError: Boolean = false,
    var type: PassErrorType = PassErrorType.NONE,
)

enum class PassErrorType {
    EMPTY,
    LESS,
    NONE
}

fun MutableState<String>.isValidEmail(): Boolean {
    return if (TextUtils.isEmpty(this.value)) false
    else Patterns.EMAIL_ADDRESS.matcher(this.value).matches()

}

fun MutableState<String>.isValidPhone(): Boolean {
    return if (TextUtils.isEmpty(this.value) || this.value.length < PHONE_MAX_LENGTH) {
        false
    } else {
        Patterns.PHONE.matcher(this.value).matches()
    }
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


fun validateLoginForm(
    mail: MutableState<String>,
    password: MutableState<String>,
    isEmailError: MutableState<Boolean> = mutableStateOf(false),
    isPasswordError: MutableState<PasswordError> = mutableStateOf(
        PasswordError(
            isError = false,
            PassErrorType.NONE
        )
    ),
    onFailedValidation: (MutableState<Boolean>, MutableState<PasswordError>) -> Unit,
    onSuccessValidation: (String, String) -> Unit,
) {

    // check phone validation or any
    if (!mail.isValidEmail()) {
        isEmailError.value = true
        onFailedValidation(isEmailError, isPasswordError)
        return
    }
    if (password.isValidPassword() == PasswordError(isError = true, PassErrorType.EMPTY)) {
        isPasswordError.value = PasswordError(isError = true, PassErrorType.EMPTY)
        onFailedValidation(isEmailError, isPasswordError)
        return
    } else if (password.isValidPassword() == PasswordError(isError = true, PassErrorType.LESS)) {
        isPasswordError.value = PasswordError(isError = true, PassErrorType.LESS)
        onFailedValidation(isEmailError, isPasswordError)
        return
    }


    onSuccessValidation(mail.value, password.value)

}


fun validateSignupForm(
    labelsState: EditeTextLabelsSignUpState,
    mail: MutableState<String>,
    firstName: MutableState<String>,
    lastName: MutableState<String>,
    password: MutableState<String>,
    confirmPassword: MutableState<String>,
    phone: MutableState<String>,
    profession: MutableState<String>,
    isEmailError: MutableState<Boolean> = mutableStateOf(false),
    isFirstNameError: MutableState<Boolean> = mutableStateOf(false),
    isLastNameError: MutableState<Boolean> = mutableStateOf(false),
    isPhoneError: MutableState<Boolean> = mutableStateOf(false),
    isProfessionError: MutableState<Boolean> = mutableStateOf(false),
    isConfirmPassError: MutableState<Boolean> = mutableStateOf(false),
    isPasswordError: MutableState<PasswordError> = mutableStateOf(
        PasswordError(
            isError = false,
            PassErrorType.NONE
        )
    ),
    onSuccessValidation: (String, String, String, String, String, String, String) -> Unit,
) {
    var foundError  = false

    // check phone validation or any
    if (!mail.isValidEmail()) {
        isEmailError.value = true
        handleTextFieldErrorSignUp(
            labelsState,
            isEmailError,
        isFirstNameError,
        isLastNameError,
        isPhoneError,
        isProfessionError,
        isConfirmPassError,
        isPasswordError,
        )
        foundError = true
    }
    if (password.isValidPassword() == PasswordError(isError = true, PassErrorType.EMPTY)) {
        isPasswordError.value = PasswordError(isError = true, PassErrorType.EMPTY)
        handleTextFieldErrorSignUp(
            labelsState,
            isEmailError,
            isFirstNameError,
            isLastNameError,
            isPhoneError,
            isProfessionError,
            isConfirmPassError,
            isPasswordError,
        )
        foundError = true

    } else if (password.isValidPassword() == PasswordError(isError = true, PassErrorType.LESS)) {
        isPasswordError.value = PasswordError(isError = true, PassErrorType.LESS)
        handleTextFieldErrorSignUp(
            labelsState,
            isEmailError,
            isFirstNameError,
            isLastNameError,
            isPhoneError,
            isProfessionError,
            isConfirmPassError,
            isPasswordError,
        )
        foundError = true

    } else if (!confirmPassword.isValidPasswordConfirm(password)) {
        isConfirmPassError.value = true
        handleTextFieldErrorSignUp(
            labelsState,
            isEmailError,
            isFirstNameError,
            isLastNameError,
            isPhoneError,
            isProfessionError,
            isConfirmPassError,
            isPasswordError,
        )
        foundError = true

    }
    if (!firstName.isValidName()) {
        isFirstNameError.value = true
        handleTextFieldErrorSignUp(
            labelsState,
            isEmailError,
            isFirstNameError,
            isLastNameError,
            isPhoneError,
            isProfessionError,
            isConfirmPassError,
            isPasswordError,
        )
        foundError = true

    }
    if (!lastName.isValidName()) {
        isLastNameError.value = true
        handleTextFieldErrorSignUp(
            labelsState,
            isEmailError,
            isFirstNameError,
            isLastNameError,
            isPhoneError,
            isProfessionError,
            isConfirmPassError,
            isPasswordError,
        )
        foundError = true

    }
    if (!phone.isValidPhone()) {
        isPhoneError.value = true
        handleTextFieldErrorSignUp(
            labelsState,
            isEmailError,
            isFirstNameError,
            isLastNameError,
            isPhoneError,
            isProfessionError,
            isConfirmPassError,
            isPasswordError,
        )
        foundError = true

    }
    if (!profession.isValidName()) {
        isProfessionError.value = true
        handleTextFieldErrorSignUp(
            labelsState,
            isEmailError,
            isFirstNameError,
            isLastNameError,
            isPhoneError,
            isProfessionError,
            isConfirmPassError,
            isPasswordError,
        )
        foundError = true

    }

    if (!foundError) {
        onSuccessValidation(
            mail.value,
            firstName.value,
            lastName.value,
            password.value,
            confirmPassword.value,
            phone.value,
            profession.value
        )
    }
}

fun validateNationalIDForm(
    id: MutableState<String>,
    isIdError: MutableState<Boolean>,
    onSuccessValidation: (String) -> Unit,
) {

    if (!id.isValidNationalID()) {
        isIdError.value = true
        return
    } else {
        isIdError.value = false
    }

    onSuccessValidation(id.value)

}


fun validateNameForm(
    name: MutableState<String>,
    isNameError: MutableState<Boolean>,
    onSuccessValidation: (String) -> Unit,
) {
    val mName = name.value.trim()
    name.value = mName

    if (!name.isValidName()) {
        isNameError.value = true
        return
    } else {
        isNameError.value = false
    }

    onSuccessValidation(mName)

}

fun handleTextFieldError(
    labelsState: EditeTextLabels,
    emailError: MutableState<Boolean>,
    passError: MutableState<PasswordError>,
) {
    labelsState.mailLabel.value = "Enter your mail "
    labelsState.mailColor = Color.Gray
    labelsState.passLabel.value = "enter your password"
    labelsState.passColor = Color.Gray
    if (emailError.value) {
        labelsState.mailLabel.value = "Enter your mail Correctly"
        labelsState.mailColor = Color.Red
    }
    if (passError.value == PasswordError(true, PassErrorType.EMPTY)) {
        labelsState.passLabel.value = "you must enter your pass"
        labelsState.passColor = Color.Red
    }
    if (passError.value == PasswordError(true, PassErrorType.LESS)) {
        labelsState.passLabel.value = "you must enter password greater that 8 digits"
        labelsState.passColor = Color.Red
    }

}

data class EditeTextLabelsSignUpState(
    val mailLabel: MutableState<String> = mutableStateOf("enter you email"),
    val firstnameLabel: MutableState<String> = mutableStateOf("First name"),
    val lastNameLabel: MutableState<String> = mutableStateOf("Last name "),
    val phoneLabel: MutableState<String> = mutableStateOf("Mobile number"),
    val passLabel: MutableState<String> = mutableStateOf("password"),
    val confirmPassLabel: MutableState<String> = mutableStateOf("confirm password"),
    val professionLabel: MutableState<String> = mutableStateOf("profession"),
    var mailColor: Color = Color.Gray,
    var firstNameColor: Color = Color.Gray,
    var lastNameColor: Color = Color.Gray,
    var phoneColor: Color = Color.Gray,
    var confirmPassColor: Color = Color.Gray,
    var professionColor: Color = Color.Gray,
    var passColor: Color = Color.Gray,

    )

fun handleTextFieldErrorSignUp(
    labelsState: EditeTextLabelsSignUpState,
    isEmailError: MutableState<Boolean> = mutableStateOf(false),
    isFirstNameError: MutableState<Boolean> = mutableStateOf(false),
    isLastNameError: MutableState<Boolean> = mutableStateOf(false),
    isPhoneError: MutableState<Boolean> = mutableStateOf(false),
    isProfessionError: MutableState<Boolean> = mutableStateOf(false),
    isConfirmPassError: MutableState<Boolean> = mutableStateOf(false),
    isPassword: MutableState<PasswordError>,
) {
    labelsState.mailLabel.value = "Enter your mail "
    labelsState.mailColor = Color.Gray
    labelsState.passLabel.value = "enter your password"
    labelsState.passColor = Color.Gray
    if (isEmailError.value) {
        labelsState.mailColor = Color.Red
    }
    if (isPassword.value == PasswordError(true, PassErrorType.EMPTY)){
        labelsState.passColor = Color.Red
    }
    if (isConfirmPassError.value) {
        labelsState.confirmPassColor = Color.Red
    }
    if (isFirstNameError.value){
        labelsState.firstNameColor = Color.Red

    }
    if (isLastNameError.value){
        labelsState.lastNameColor = Color.Red
    }
    if (isPhoneError.value){
        labelsState.phoneColor = Color.Red
    }
    if (isProfessionError.value){
        labelsState.professionColor = Color.Red
    }

}
