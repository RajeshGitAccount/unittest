package com.sample

import java.util.regex.Pattern


//return true if email is not valid
fun String.emailValidation(): Boolean {
    val emailRegex = "[a-zA-Z0-9._-]+@[a-z-_]+\\.+[a-z]+"
    return !(this.matches(emailRegex.toRegex()))
}

//returns true if mobile no is valid
fun String.isValidMobileNo(): Boolean {
    val reg = "^(\\+91[\\-\\s]?)?[0]?(91)?[6789]\\d{9}\$"
    val pattern: Pattern = Pattern.compile(reg)
    return pattern.matcher(this).find()
}

