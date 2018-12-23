package com.management.engineering.alarm.alarmengineermanagement.features.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.management.engineering.alarm.alarmengineermanagement.R
import com.management.engineering.alarm.alarmengineermanagement.data.models.CompanyResponse
import com.management.engineering.alarm.alarmengineermanagement.data.models.Resource
import com.management.engineering.alarm.alarmengineermanagement.utils.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        view.text_company_name.text = arguments?.get(ARG_COMPANY_NAME)?.toString()
        view.text_user_role.text = arguments?.get(ARG_USER_ROLE)?.toString()
        view.text_current_date.text = getCurrentDateTime().toString("dd/MM/yyyy HH:mm")

        if (arguments?.get(ARG_USER_FIRST_NAME)?.toString() != null && arguments?.get(ARG_USER_LAST_NAME)?.toString() != null) {
            view.text_user_full_name.text = String.format("%s %s", arguments?.get(ARG_USER_FIRST_NAME)?.toString(), arguments?.get(ARG_USER_LAST_NAME)?.toString())
        }

        viewModel.getCompany().observe(this,
                Observer<Resource<CompanyResponse>> { resource ->
                    if (resource != null) {
                        when (resource.status) {
                            Resource.Status.SUCCESS -> {
                                val company = resource.data?.name
                                val modules = resource.data?.modules
                                val x = 2
                            }

                            Resource.Status.FAILED -> {

                            }

                            Resource.Status.ERROR -> {

                            }
                        }
                    }
                })

        return view
    }
}