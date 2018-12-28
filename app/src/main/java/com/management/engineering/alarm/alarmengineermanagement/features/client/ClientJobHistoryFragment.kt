package com.management.engineering.alarm.alarmengineermanagement.features.client

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.management.engineering.alarm.alarmengineermanagement.R
import com.management.engineering.alarm.alarmengineermanagement.data.models.ClientResponse
import com.management.engineering.alarm.alarmengineermanagement.utils.ARG_CLIENT
import org.koin.androidx.viewmodel.ext.android.viewModel

class ClientJobHistoryFragment : Fragment() {

    companion object {

        fun newInstance(clientResponse: ClientResponse): ClientJobHistoryFragment {
            val fragment = ClientJobHistoryFragment()
            val bundle = bundleOf(ARG_CLIENT to clientResponse)
            fragment.arguments = bundle

            return fragment
        }
    }

    private val viewModel: ClientViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_client_job_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.client = arguments?.getParcelable(ARG_CLIENT)!!
    }
}