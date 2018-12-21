package com.management.engineering.alarm.alarmengineermanagement.features.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import com.management.engineering.alarm.alarmengineermanagement.R
import com.management.engineering.alarm.alarmengineermanagement.data.models.LoginPostResponse
import com.management.engineering.alarm.alarmengineermanagement.data.models.Resource
import com.management.engineering.alarm.alarmengineermanagement.utils.PreferencesHelper
import kotlinx.android.synthetic.main.fragment_login.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        view.findViewById<AppCompatButton>(R.id.btn_login).setOnClickListener {
            onLogin(view)
        }

        return view
    }

    private fun onLogin(view: View) {
        if (view.edit_text_username.text?.length != 0 && view.edit_text_password.text?.length != 0) {
            view.btn_login.isEnabled = false

            viewModel.postLogin(view.edit_text_username.text.toString(), view.edit_text_password.text.toString()).observe(this, Observer<Resource<LoginPostResponse>> { resource ->
                if (resource != null) {
                    when (resource.status) {
                        Resource.Status.SUCCESS -> {
                            PreferencesHelper(context!!.applicationContext).token = resource.data?.token.toString()
                            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_homeFragment)
                        }

                        Resource.Status.FAILED -> {
                            if (resource.data?.error != null) {
                                showErrorSnackbar(resource.data.error[0], view)
                                view.btn_login.isEnabled = true
                            }
                        }

                        Resource.Status.ERROR -> {
                            showErrorSnackbar("Error: " + resource.exception?.exceptin?.message, view)
                            view.btn_login.isEnabled = true
                        }
                    }
                }
            })
        } else {
            showErrorSnackbar(getString(R.string.fill_in_all_fields_error), view)
        }
    }

    private fun showErrorSnackbar(message: String, view: View) {
        Snackbar.make(
                view,
                message,
                Snackbar.LENGTH_SHORT
        ).show()
    }

}