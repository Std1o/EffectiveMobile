package com.stdio.effectivemobile.ui

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.stdio.effectivemobile.R
import com.stdio.effectivemobile.common.extensions.viewBinding
import com.stdio.effectivemobile.databinding.FragmentCoursesHostBinding

class CoursesHostFragment : Fragment(R.layout.fragment_courses_host) {

    private val binding by viewBinding(FragmentCoursesHostBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navView: BottomNavigationView = binding.navView
        navView.itemActiveIndicatorColor = requireActivity().getColorStateList(R.color.light_gray)

        val navController =
            requireActivity().findNavController(R.id.nav_host_fragment_activity_main_host)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        navView.setupWithNavController(navController)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            requireActivity().window.navigationBarColor = ContextCompat.getColor(requireActivity(), R.color.dark_gray)
        }
    }
}