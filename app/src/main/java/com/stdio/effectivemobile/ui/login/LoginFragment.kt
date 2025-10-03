package com.stdio.effectivemobile.ui.login

import android.content.Intent
import android.net.Uri
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
import androidx.core.net.toUri

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
        addTextWatchersForCheckInput()
        viewModel.isButtonEnabled.observe(viewLifecycleOwner, result = {
            binding.button.isEnabled = it
        })

        binding.btnVk.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, "https://vk.com/".toUri())
            startActivity(browserIntent)
        }
        binding.btnOk.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, "https://ok.ru/".toUri())
            startActivity(browserIntent)
        }
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
        binding.etEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                s?.let {
                    val text = it.toString()
                    val cyrillicPattern = "[а-яА-ЯёЁ]".toRegex()

                    if (cyrillicPattern.containsMatchIn(text)) {
                        val filteredText = cyrillicPattern.replace(text, "")
                        binding.etEmail.setText(filteredText)
                        binding.etEmail.setSelection(filteredText.length)
                    }
                }
            }
        })
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