package com.engineer.imitate.ui.fragments.subs


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.engineer.imitate.R
import com.engineer.imitate.ui.activity.StarShowActivity
import kotlinx.android.synthetic.main.include_shadow_layout.*


/**
 * A simple [Fragment] subclass.
 */
class ShadowLayoutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shadow_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonPanel.setOnClickListener {
            startActivity(
                Intent(context, StarShowActivity::class.java)
            )
        }
    }
}
