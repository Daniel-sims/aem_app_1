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
import kotlinx.android.synthetic.main.fragment_client.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ClientFragment : Fragment() {

    private val viewModel: ClientViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_client, container, false)
        view.toolbar_client.setNavigationOnClickListener { Navigation.findNavController(view).navigateUp() }

        viewModel.client = arguments?.getParcelable(ARG_CLIENT)!!
        view.toolbar_client.title = viewModel.client.name

        view.bottom_nav_client.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        return view
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.action_item1 -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.action_item2 -> {

                return@OnNavigationItemSelectedListener true
            }
            R.id.action_item3 -> {

                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}