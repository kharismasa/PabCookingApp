package com.example.bismillah.ui.fragments.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.bismillah.R
import com.example.bismillah.preferensi.HalamanEditPreferensi


class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate layout untuk fragment ini
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Temukan TextView berdasarkan ID
        val dietaryPreferenceTextView: TextView = view.findViewById(R.id.dietaryp)

        // Tambahkan listener klik
        dietaryPreferenceTextView.setOnClickListener {
            // Intent untuk membuka HalamanEditPreferensi
            val intent = Intent(activity, HalamanEditPreferensi::class.java)
            startActivity(intent)
        }
    }
}