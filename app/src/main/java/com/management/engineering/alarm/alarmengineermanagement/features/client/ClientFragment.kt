package com.management.engineering.alarm.alarmengineermanagement.features.client

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.management.engineering.alarm.alarmengineermanagement.R
import com.management.engineering.alarm.alarmengineermanagement.utils.ARG_CLIENT
import kotlinx.android.synthetic.main.fragment_client.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class ClientFragment : Fragment() {

    private val viewModel: ClientViewModel by viewModel()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_client, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState);

        viewModel.client = arguments?.getParcelable(ARG_CLIENT)!!

        toolbar_client.title = viewModel.client.name
        toolbar_client.setNavigationOnClickListener { Navigation.findNavController(view).navigateUp() }

        val fragmentContainer = view.findViewById<View>(R.id.fl_client_nav_container)
        val navController = Navigation.findNavController(fragmentContainer)
        bottom_nav_client.setupWithNavController(navController)
    }
}
