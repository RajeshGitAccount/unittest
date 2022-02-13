package com.kapilguru.trainer.login.models

import androidx.annotation.Keep

@Keep
data class LoginResponse(
    val token: String,
)