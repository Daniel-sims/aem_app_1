package com.management.engineering.alarm.alarmengineermanagement.features.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.management.engineering.alarm.alarmengineermanagement.R
import com.management.engineering.alarm.alarmengineermanagement.utils.PreferencesHelper
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_home, container, false)
        view?.text_title?.text = arguments?.getString("title")

        view.findViewById<AppCompatButton>(R.id.btn_stylesheet).setOnClickListener {
            Navigation.findNavController(view).navigate(
                    R.id.action_homeFragment_to_styleSheetFragment
            )
        }

        val token = PreferencesHelper(context!!.applicationContext).token
        Toast.makeText(context, token, LENGTH_LONG).show()

        return view
    }
}