package com.example.platformforschoolchildren.presentation.fragments

import android.util.Patterns
import android.view.LayoutInflater
import android.widget.Toast
import com.example.platformforschoolchildren.core.base.BaseFragment
import com.example.platformforschoolchildren.databinding.FragmentForgotPasswordBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class ForgotPasswordFragment : BaseFragment<FragmentForgotPasswordBinding>() {

    private lateinit var mAuth : FirebaseAuth

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