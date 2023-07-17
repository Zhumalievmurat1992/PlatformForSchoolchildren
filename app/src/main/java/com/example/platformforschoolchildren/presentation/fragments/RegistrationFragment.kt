package com.example.platformforschoolchildren.presentation.fragments

import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.LayoutInflater
import androidx.navigation.fragment.findNavController
import com.example.platformforschoolchildren.R
import com.example.platformforschoolchildren.core.base.BaseFragment
import com.example.platformforschoolchildren.databinding.FragmentRegistrationBinding

class RegistrationFragment : BaseFragment<FragmentRegistrationBinding>() {

    override fun inflateViewBinding(inflater: LayoutInflater) =
        FragmentRegistrationBinding.inflate(inflater)

    override fun initListener() {
        super.initView()

        emailFocusListener()

        binding.btnResume.setOnClickListener {
            findNavController().navigate(R.id.regForSchoolchildrenFragment)
        }

        binding.accountEnter.setOnClickListener {
            findNavController().navigate(R.id.authFragment)
        }

        binding.name.addTextChangedListener(watcher)
        binding.lastName.addTextChangedListener(watcher)
        binding.password.addTextChangedListener(watcher)
    }

    private val watcher = object : TextWatcher {

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            val userName = binding.name.text.toString().trim()
            val lastName = binding.lastName.text.toString().trim()
            val password = binding.password.text.toString().trim()
            binding.btnResume.isEnabled =
                userName.isNotEmpty() && lastName.isNotEmpty() && password.isNotEmpty()
        }

        override fun afterTextChanged(p0: Editable?) {}

    }
    private fun emailFocusListener() {
        binding.emailEdTxt.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.emailContainer.helperText = validEmail()
            }
        }

    }

    private fun validEmail(): String? {
        val emailText = binding.emailEdTxt.text.toString()
        if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            return "не удалось найти аккаунт"
        }
        return null
    }

}

