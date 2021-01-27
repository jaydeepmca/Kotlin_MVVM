package com.example.kotlin_mvvm.ui.viewmodel

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.kotlin_login_mvvm.util.SingleLiveEvent
import com.example.kotlin_login_mvvm.util.Util
import com.example.kotlin_mvvm.model.LoginResponseModel
import com.example.kotlin_mvvm.network.BackEndApi
import com.example.kotlin_mvvm.network.WebServiceClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(application: Application) : AndroidViewModel(application), Callback<LoginResponseModel> {

    var btnSelected: ObservableBoolean? = null
    var email: ObservableField<String>? = null
    var password: ObservableField<String>? = null
    var actionn: ObservableField<Int>? = null
    var progressDialog: SingleLiveEvent<Boolean>? = null
    var userLogin: MutableLiveData<LoginResponseModel>? = null

    init {
        btnSelected = ObservableBoolean(false)
        progressDialog = SingleLiveEvent<Boolean>()
        email = ObservableField("")
        password = ObservableField("")
        actionn = ObservableField(1)
        userLogin = MutableLiveData<LoginResponseModel>()
    }

    fun onEmailChanged(s: CharSequence, start: Int, befor: Int, count: Int) {
        btnSelected?.set(Util.isEmailValid(s.toString()) && password?.get()!!.length >= 2)


    }

    fun onPasswordChanged(s: CharSequence, start: Int, befor: Int, count: Int) {
        btnSelected?.set(Util.isEmailValid(email?.get()!!) && s.toString().length >= 2)


    }


    fun login() {
        progressDialog?.value = true
        WebServiceClient.client.create(BackEndApi::class.java).doLogin(
            USERCODE = email?.get()!!, PASSWORD = password?.get()!!, ACTION= actionn?.get()!!
        )
            .enqueue(this)

    }


    override fun onResponse(
        call: Call<LoginResponseModel>?,
        response: Response<LoginResponseModel>?
    ) {
        progressDialog?.value = false
        userLogin?.value = response?.body()
    }

    override fun onFailure(call: Call<LoginResponseModel>?, t: Throwable?) {
        progressDialog?.value = false
    }
}