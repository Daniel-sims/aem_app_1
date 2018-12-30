package com.management.engineering.alarm.alarmengineermanagement.features.client

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.management.engineering.alarm.alarmengineermanagement.R
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ClientJobHistoryFragment : Fragment() {

    private val viewModel: ClientViewModel by sharedViewModel()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_client_job_history, container, false)
}