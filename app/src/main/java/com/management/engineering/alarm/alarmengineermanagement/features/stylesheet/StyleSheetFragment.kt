package com.management.engineering.alarm.alarmengineermanagement.features.stylesheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.management.engineering.alarm.alarmengineermanagement.R

class StyleSheetFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_style_sheet, container, false)

        return view
    }

}