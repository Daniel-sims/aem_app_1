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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_login.setOnClickListener {
            onLogin(view)
        }
    }

    private fun onLogin(view: View) =
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
                                                // Store the token in shared preferences
                                                PreferencesHelper(context!!.applicationContext).token =
                                                        resource.data?.token.toString()

                                                // Store the token in shared preferences
                                                PreferencesHelper(context!!.applicationContext).companyId =
                                                        resource.data!!.companyPk

                                                Navigation.findNavController(view).navigate(
                                                        R.id.action_loginFragment_to_homeFragment,
                                                        bundleOf(
                                                                ARG_COMPANY_NAME to resource.data.companyName,
                                                                ARG_USER_ROLE to resource.data.role,
                                                                ARG_USER_FIRST_NAME to resource.data.firstName,
                                                                ARG_USER_LAST_NAME to resource.data.lastName
                                                        )
                                                )
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

    private fun showErrorSnackbar(message: String, view: View) {
        Snackbar.make(
                view,
                message,
                Snackbar.LENGTH_SHORT
        ).show()
    }
}