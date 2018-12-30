package com.management.engineering.alarm.alarmengineermanagement.features.customer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.management.engineering.alarm.alarmengineermanagement.R
import com.management.engineering.alarm.alarmengineermanagement.utils.ARG_CUSTOMER
import kotlinx.android.synthetic.main.fragment_customer.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CustomerFragment : Fragment() {

    private val viewModel: CustomerViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_customer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.customer = arguments?.getParcelable(ARG_CUSTOMER)!!

        toolbar_customer.setNavigationOnClickListener { Navigation.findNavController(view).navigateUp() }
        toolbar_customer.title = viewModel.customer.name

        initCustomerDetails()
    }

    private fun initCustomerDetails() {
        text_customer_details_name.text = viewModel.customer.name
        text_customer_details_account_number.text = viewModel.customer.accountNumber
        text_customer_details_mobile_number.text = viewModel.customer.mobileNumber
        text_customer_details_landline_number.text = viewModel.customer.landlineNumber
        text_customer_details_email.text = viewModel.customer.email
        text_customer_details_address.text = "NOT YET IMPLEMENTED"

        text_customer_description_body.text = viewModel.customer.description
        text_customer_system_details_body.text = viewModel.customer.systemDetails
    }
}