package com.sample

import com.kapilguru.trainer.login.models.LoginResponse
import retrofit2.http.*

interface ApiKapilTutorService {

    @POST("api/login")
    suspend fun userLogin(@Body loginUserRequest: LoginUserRequest): LoginResponse


}