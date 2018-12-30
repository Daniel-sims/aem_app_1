package com.management.engineering.alarm.alarmengineermanagement.features.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import com.management.engineering.alarm.alarmengineermanagement.R
import com.management.engineering.alarm.alarmengineermanagement.data.models.LoginPostResponse
import com.management.engineering.alarm.alarmengineermanagement.data.models.Resource
import com.management.engineering.alarm.alarmengineermanagement.utils.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModel()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_login, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_login.setOnClickListener {
            onLogin()
        }
    }

    private fun onLogin() =
            view?.let { view ->
                if (view.edit_text_username.text?.length != 0 && view.edit_text_password.text?.length != 0) {
                    view.hideKeyboard()
                    view.btn_login.isEnabled = false

                    viewModel.postLogin(
                            view.edit_text_username.text.toString(),
                            view.edit_text_password.text.toString())
                            .observe(this,
                                    Observer<Resource<LoginPostResponse>> { resource ->
                                        if (resource != null) {
                                            when (resource.status) {
                                                Resource.Status.SUCCESS -> {
                                                    resource.data?.let { data ->
                                                        context?.let { context ->
                                                            PreferencesHelper(context.applicationContext).token =
                                                                    data.token.toString()

                                                            PreferencesHelper(context.applicationContext).companyId =
                                                                    data.companyPk
                                                        }

                                                        Navigation.findNavController(view).navigate(
                                                                R.id.action_loginFragment_to_homeFragment,
                                                                bundleOf(
                                                                        ARG_COMPANY_NAME to data.companyName,
                                                                        ARG_USER_ROLE to data.role,
                                                                        ARG_USER_FIRST_NAME to data.firstName,
                                                                        ARG_USER_LAST_NAME to data.lastName
                                                                )
                                                        )
                                                    }
                                                }

                                                Resource.Status.FAILED -> {
                                                    resource.data?.let { data ->
                                                        if (data.error != null) {

                                                            showErrorSnackbar(data.error[0])
                                                            view.btn_login.isEnabled = true
                                                        }
                                                    }
                                                }

                                                Resource.Status.ERROR -> {
                                                    showErrorSnackbar("Error: " + resource.exception?.exceptin?.message)
                                                    view.btn_login.isEnabled = true
                                                }
                                            }
                                        }
                                    })
                } else {
                    showErrorSnackbar(getString(R.string.fill_in_all_fields_error))
                }

            }

    private fun showErrorSnackbar(message: String) {
        view?.let { view ->
            Snackbar.make(
                    view,
                    message,
                    Snackbar.LENGTH_SHORT
            ).show()
        }
    }
}