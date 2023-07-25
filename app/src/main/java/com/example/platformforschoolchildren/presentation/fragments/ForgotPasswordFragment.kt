package com.example.platformforschoolchildren.presentation.fragments

import android.util.Patterns
import android.view.LayoutInflater
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.core.base.BaseFragment
import com.example.platformforschoolchildren.databinding.FragmentForgotPasswordBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.example.platformforschoolchildren.presentation.fragments.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgotPasswordFragment : BaseFragment<FragmentForgotPasswordBinding,AuthViewModel>() {

    private lateinit var mAuth : FirebaseAuth

    override val viewModel: AuthViewModel by lazy {
        ViewModelProvider(this)[AuthViewModel::class.java]
    }

    override fun inflateViewBinding(inflater: LayoutInflater) =
        FragmentForgotPasswordBinding.inflate(inflater)

    override fun initListener() {
        super.initListener()
        mAuth = Firebase.auth
        emailFocusListener()
        binding.btnSend.setOnClickListener {
           val sPassword = binding.emailEdTxt.text.toString()
            mAuth.sendPasswordResetEmail(sPassword)
                .addOnSuccessListener {

                    Toast.makeText(requireContext(), "Проверьте почту пожалуйста", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
                }
        }
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
            return "Не верный адрес"
        }
        return null
    }



}