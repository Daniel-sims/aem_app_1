package com.management.engineering.alarm.alarmengineermanagement.features.client

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.management.engineering.alarm.alarmengineermanagement.R
import com.management.engineering.alarm.alarmengineermanagement.data.models.ClientResponse
import com.management.engineering.alarm.alarmengineermanagement.data.models.CustomerResponse
import com.management.engineering.alarm.alarmengineermanagement.utils.ARG_CLIENT
import com.management.engineering.alarm.alarmengineermanagement.utils.ARG_CUSTOMER
import com.management.engineering.alarm.alarmengineermanagement.utils.adapters.CustomersAdapter
import kotlinx.android.synthetic.main.fragment_client_customers.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ClientCustomersFragment : Fragment() {

    companion object {

        fun newInstance(clientResponse: ClientResponse): ClientCustomersFragment {
            val fragment = ClientCustomersFragment()
            val bundle = bundleOf(ARG_CLIENT to clientResponse)
            fragment.arguments = bundle

            return fragment
        }
    }

    private val viewModel: ClientViewModel by viewModel()
    private lateinit var customersAdapter: CustomersAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_client_customers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //viewModel.client = arguments?.getParcelable(ARG_CLIENT)!!

        //initCustomers()
    }

    private fun initCustomers() {
        if (viewModel.client.customers.isEmpty()) {
            layout_no_customers.visibility = View.VISIBLE
        } else {
            rv_customers.visibility = View.VISIBLE
            rv_customers.layoutManager = LinearLayoutManager(context)

            customersAdapter = CustomersAdapter(viewModel.client.customers as ArrayList<CustomerResponse>)
            rv_customers.adapter = customersAdapter

            customersAdapter.onCustomerClicked = { customer ->
                Navigation.findNavController(view!!).navigate(R.id.action_clientFragment_to_customerFragment,
                        bundleOf(ARG_CUSTOMER to customer))
            }
        }
    }


}