package com.kapilguru.trainer.login.models

import androidx.annotation.Keep
import com.google.gson.annotations.Expose

@Keep
data class Data(
    val auth: Boolean,
    val contactNumber: String,
    val email: String,
    val isAdmin: Int,
    val isStudent: Int,
    val isTrainer: Int,
    val token: String,
    val user_id: Int,
    val username: String,
    val isProfileUpdated : Int,
    val isBankUpdated : Int,
    val isImageUpdated : Int,
    val user_code : String,
    val isSubscribed : Int,
)