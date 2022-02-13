package com.sample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kapilguru.trainer.login.models.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

open class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val TAG = "LoginViewModel"

    var userName: MutableLiveData<String> = MutableLiveData()
    var password: MutableLiveData<String> = MutableLiveData()
    var resultDat: MutableLiveData<ApiResource<LoginResponse>> = MutableLiveData()
    var errorOnValidations: MutableLiveData<InValidErrors> = MutableLiveData()
    var abc : String ="sd"

    init {
        userName.value = "sample@gmail.com"
        password.value = "jfhudgf"
    }

    // used for unit testing purpose
   open fun isInputValid(mailOrMobile: String?, password: String?): Boolean {
        arrayNotNulls(mailOrMobile,password)?.let {(mail,number)->
            return when {
                mail.trim().emailValidation() -> false
                number.trim().isEmpty() -> false
                else -> true
            }
        }?: kotlin.run {
            return false
        }
    }

   open fun  getresultDat(): MutableLiveData<ApiResource<LoginResponse>> = resultDat

    // used for unit testing purpose
    fun <T: Any> arrayNotNulls(vararg elements:T?): Array<T>? {
        if (null in elements) {
            return null
        }
        return elements as Array<T>
    }

    // used for unit testing purpose
   open fun onClickCheck() {

        if(!isInputValid(userName.value, password.value)){
            setUpLoginRequest(userName.value!!,password.value!!)
        }
    }

    private fun setUpLoginRequest(userName: String, password: String) {
        loginUserApi(LoginUserRequest(emailId = userName,password = password))
    }

    open fun loginUserApi(loginRequest: LoginUserRequest) {
        resultDat.value = ApiResource.loading(data = null)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                resultDat.postValue(ApiResource.success(loginRepository.getUsers(loginRequest)))
            } catch (exception: IOException) {
                resultDat.postValue(ApiResource.error(data = null, message = exception.message ?: "Error Occurred!"))
            } catch (exception: HttpException) {
                resultDat.postValue(ApiResource.error(data = null, message = exception.message ?: "Error Occurred!", exception.code()))
            }
        }
    }
}


enum class InValidErrors {
    PASSWORDINCORRECT, EMAILINCORRECT, EMAIL_OR_MOBILE_EMPTY, MOBILE_IN_CORRECT,PASSWORD_EMPTY
}