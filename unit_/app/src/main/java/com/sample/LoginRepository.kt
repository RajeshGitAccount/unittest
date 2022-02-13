package com.sample


open class LoginRepository(private val apiHelper: ApiHelper) {

    suspend fun getUsers(loginUserRequest: LoginUserRequest) = apiHelper.getUsers(loginUserRequest)


}