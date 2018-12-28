package com.management.engineering.alarm.alarmengineermanagement.features.client

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.management.engineering.alarm.alarmengineermanagement.R
import com.management.engineering.alarm.alarmengineermanagement.utils.ARG_CLIENT
import kotlinx.android.synthetic.main.fragment_client.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ClientFragment : Fragment() {

    private val viewModel: ClientViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_client, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBottomNav()

        viewModel.client = arguments?.getParcelable(ARG_CLIENT)!!

        toolbar_client.title = viewModel.client.name
        toolbar_client.setNavigationOnClickListener { Navigation.findNavController(view).navigateUp() }

        swapFragment(ClientDetailsFragment.newInstance(viewModel.client))
    }

    private fun initBottomNav() {
        bottom_nav_client.setOnNavigationItemSelectedListener(
                BottomNavigationView.OnNavigationItemSelectedListener { item ->
                    when (item.itemId) {
                        R.id.client_action_details -> {
                            swapFragment(ClientDetailsFragment.newInstance(viewModel.client))
                            return@OnNavigationItemSelectedListener true
                        }

                        R.id.client_action_job_history -> {
                            swapFragment(ClientJobHistoryFragment.newInstance(viewModel.client))
                            return@OnNavigationItemSelectedListener true
                        }

                        R.id.client_action_customers -> {
                            swapFragment(ClientCustomersFragment.newInstance(viewModel.client))
                            return@OnNavigationItemSelectedListener true
                        }
                    }

                    false
                })
    }

    private fun swapFragment(fragment: Fragment) {
        fragmentManager?.beginTransaction()?.replace(R.id.fl_client_nav_container, fragment)?.commit()
    }
}