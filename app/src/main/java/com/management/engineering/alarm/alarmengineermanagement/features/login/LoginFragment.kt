package com.management.engineering.alarm.alarmengineermanagement.features.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.management.engineering.alarm.alarmengineermanagement.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    val viewModel: LoginViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        view.findViewById<AppCompatButton>(R.id.btn_login).setOnClickListener {
            onLogin(view)
        }

        return view
    }

    private fun onLogin(view: View) {
        Navigation.findNavController(view).navigate(
                R.id.action_loginFragment_to_homeFragment)
    }

}