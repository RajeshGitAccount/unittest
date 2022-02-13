package com.sample


open class ApiHelper(private val apiKapilTutorService: ApiKapilTutorService) {

    suspend fun getUsers(loginUserRequest: LoginUserRequest) = apiKapilTutorService.userLogin(loginUserRequest)


}
