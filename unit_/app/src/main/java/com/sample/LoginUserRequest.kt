package com.sample

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep open class LoginUserRequest(
    @SerializedName("email") val emailId: String,
    @SerializedName("password") val password: String,
)