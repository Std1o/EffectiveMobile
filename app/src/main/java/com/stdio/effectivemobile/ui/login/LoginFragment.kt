package com.stdio.effectivemobile.ui.login

import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.stdio.core.common.flow.observe
import com.stdio.effectivemobile.R
import com.stdio.effectivemobile.app.App
import com.stdio.effectivemobile.common.extensions.viewBinding
import com.stdio.effectivemobile.databinding.FragmentLoginBinding
import com.stdio.effectivemobile.di.LoginViewModelFactory
import com.stdio.effectivemobile.ui.home.HomeViewModel
import javax.inject.Inject
import kotlin.getValue

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val binding by viewBinding(FragmentLoginBinding::bind)

    @Inject
    @LoginViewModelFactory
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: LoginViewModel by viewModels { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity().application as App).appComponent.inject(this)
        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_coursesHostFragment)
        }
        disableCyrillic()
        addTextWatchersForCheckInput()
        viewModel.isButtonEnabled.observe(viewLifecycleOwner, result = {
            binding.button.isEnabled = it
        })
    }

    private fun disableCyrillic() {
        val filter = InputFilter { source, start, end, dest, dstart, dend ->
            for (i in start until end) {
                if (Character.UnicodeBlock.of(source[i]) == Character.UnicodeBlock.CYRILLIC) {
                    return@InputFilter ""
                }
            }
            null
        }

        binding.etEmail.filters = arrayOf(filter)
    }

    private fun addTextWatchersForCheckInput() {
        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(str: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.checkIsValid(
                    email = binding.etEmail.text.toString(),
                    password = binding.etPassword.text.toString()
                )
            }

        }
        binding.etEmail.addTextChangedListener(textWatcher)
        binding.etPassword.addTextChangedListener(textWatcher)
    }

    override fun onResume() {
        super.onResume()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            requireActivity().window.navigationBarColor = ContextCompat.getColor(requireActivity(), R.color.background)
        }
    }
}