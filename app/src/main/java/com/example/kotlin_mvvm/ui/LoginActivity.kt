package com.example.kotlin_mvvm.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.kotlin_login_mvvm.util.CustomeProgressDialog
import com.example.kotlin_mvvm.R
import com.example.kotlin_mvvm.databinding.ActivityLoginBinding
import com.example.kotlin_mvvm.ui.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {
    var binding: ActivityLoginBinding? = null
    var viewmodel: LoginViewModel? = null
    var customeProgressDialog: CustomeProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        viewmodel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        binding?.viewmodel = viewmodel
        customeProgressDialog = CustomeProgressDialog(this)
        initObservables()
    }

    private fun initObservables() {
        viewmodel?.progressDialog?.observe(this, Observer {
            if (it!!) customeProgressDialog?.show() else customeProgressDialog?.dismiss()
        })

        viewmodel?.userLogin?.observe(this, Observer { user ->
            if (user?.EMPLOYEENAME.toString().equals("null")) {
               // Toast.makeText(this, "${user?.RESPONSEMESSAGE}", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "welcome, ${user?.EMPLOYEENAME}", Toast.LENGTH_LONG).show()
            }
        })
    }
}