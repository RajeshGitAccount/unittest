package com.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    lateinit var viewModel: LoginViewModel
    lateinit var bind: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(
            this,
            LoginViewModelFactory(ApiHelper(RetrofitNetwork.API_KAPIL_TUTOR_SERVICE_SERVICE))
        ).get(LoginViewModel::class.java)
        bind.loginViewModel = viewModel
        bind.lifecycleOwner = this

        setClickListeners()
        observeViewModelData()
    }


    private fun setClickListeners() {
        bind.click.setOnClickListener {
            viewModel.onClickCheck()
        }
    }


    private fun observeViewModelData() {
        // API Call for login obserer
        viewModel.resultDat.observe(this, Observer {
            when (it.status) {
                Status.LOADING -> {

                }

                Status.SUCCESS -> {
                    //
                    // do some stuff
                }

                Status.ERROR -> {
//                    dialog.dismissLoadingDialog()
                    when (it.code) {
                        400 -> {
//                            showErrorDialog(resources.getString(R.string.invalid_credentials))
                        }
                        422 -> {
//                            showErrorDialog(resources.getString(R.string.user_not_registered))
                        }
                        208 -> {
//                            showErrorDialog(resources.getString(R.string.login_exceeded))
                        }
                        else -> {
//                            showErrorDialog(getString(R.string.try_again))
                        }
                    }
                }
            }
        })

        viewModel.errorOnValidations.observe(this, Observer {
            when (it) {
                InValidErrors.EMAILINCORRECT -> {
//                    showErrorDialog(getString(R.string.invalid_email))
                }
                InValidErrors.MOBILE_IN_CORRECT -> {
//                    showErrorDialog(getString(R.string.invalid_mobile))
                }
                InValidErrors.PASSWORD_EMPTY -> {
//                    showErrorDialog(getString(R.string.empty_password))
                }
                InValidErrors.PASSWORDINCORRECT -> {
//                    showErrorDialog(getString(R.string.invalid_password))
                }
            }
        })
    }


    private fun navigateToHomeActivity() {

    }

    private fun showErrorDialog(message: String) {
        val alertDialog: AlertDialog? = this.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setPositiveButton("ok") { _, _ ->
                    setCancelable(true)
                }
                setMessage(message)
            }
            builder.create()
        }
        alertDialog?.show()
    }


}