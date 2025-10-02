package com.stdio.effectivemobile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.stdio.effectivemobile.common.extensions.viewBinding
import com.stdio.effectivemobile.databinding.FragmentCoursesHostBinding
import com.stdio.effectivemobile.databinding.FragmentCoursesHostBinding.bind
import com.stdio.effectivemobile.databinding.FragmentLoginBinding

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val binding by viewBinding(FragmentLoginBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_coursesHostFragment)
        }
    }
}