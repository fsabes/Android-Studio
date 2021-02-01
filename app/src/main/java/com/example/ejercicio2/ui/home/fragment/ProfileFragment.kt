package com.example.ejercicio2.ui.home.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.TextView
import com.example.ejercicio2.R
import com.example.ejercicio2.entities.Client
import com.example.ejercicio2.ui.start.StartActivity

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var tvName: TextView = view.findViewById(R.id.tv_profile_name)

        tvName.text = "${
            activity?.intent?.getParcelableExtra<Client>(StartActivity.CLIENT)?.getName()
                ?.capitalize()
        }"
    }
}