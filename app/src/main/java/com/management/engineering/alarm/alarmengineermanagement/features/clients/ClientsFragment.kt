package com.management.engineering.alarm.alarmengineermanagement.features.clients

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.management.engineering.alarm.alarmengineermanagement.R
import kotlinx.android.synthetic.main.fragment_clients.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ClientsFragment : Fragment() {

    private val viewModel: ClientsViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_clients, container, false)
        view.toolbar_clients.setNavigationOnClickListener { Navigation.findNavController(view).navigateUp() }

        return view
    }
}