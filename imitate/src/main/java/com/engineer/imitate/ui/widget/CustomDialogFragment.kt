package com.engineer.imitate.ui.widget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.engineer.imitate.R

/**
 *
 * @author: Rookie
 * @date: 2018-07-31 15:00
 * @version V1.0
 */

class CustomDialogFragment: androidx.fragment.app.DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_dialog,container,false)
        return view
    }
}