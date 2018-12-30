package com.management.engineering.alarm.alarmengineermanagement.features.client

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.management.engineering.alarm.alarmengineermanagement.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class ClientDetailsFragment : Fragment() {

    private val viewModel: ClientViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_client_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       // viewModel.client = arguments?.getParcelable(ARG_CLIENT)!!

        initClientDetails()
    }

    private fun initClientDetails() {
//        text_client_details_name.text = viewModel.client.name
//        text_client_details_account_number.text = viewModel.client.accountNumber
//        text_client_details_mobile_number.text = viewModel.client.mobileNumber
//        text_client_details_landline_number.text = viewModel.client.landlineNumber
//        text_client_details_email.text = viewModel.client.email
//        text_client_details_address.text = "NOT YET IMPLEMENTED"
//
//        text_client_description_body.text = viewModel.client.description
//        text_client_system_details_body.text = viewModel.client.systemDetails
    }
}