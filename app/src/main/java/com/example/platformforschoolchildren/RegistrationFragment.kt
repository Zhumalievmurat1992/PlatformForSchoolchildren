package com.example.platformforschoolchildren

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.platformforschoolchildren.databinding.FragmentRegistrationBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegistrationFragment : Fragment() {

     private lateinit var binding: FragmentRegistrationBinding
     private lateinit var mAuth : FirebaseAuth

    override fun onStart() {
        super.onStart()

        val currentUser = mAuth.currentUser
        if (currentUser != null) {
            findNavController().navigate(R.id.go_to_authFragment)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegistrationBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         init()
        binding.register.setOnClickListener {

            val email = binding.email.text.toString()
            val password = binding.password.text.toString()
            if (TextUtils.isEmpty(email)){
                Toast.makeText(
                    requireContext(),
                    "введите почту",
                    Toast.LENGTH_SHORT,
                ).show()
            }
            if (TextUtils.isEmpty(password)){
                Toast.makeText(
                    requireContext(),
                    "введите пароль",
                    Toast.LENGTH_SHORT,
                ).show()
            }

            createUser(email,password)
            binding.progressBar.visibility = View.VISIBLE
        }
    }

    private fun init() {
        mAuth = Firebase.auth
    }

    private fun createUser(email:String,password:String) {

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                binding.progressBar.visibility = View.GONE
                if (task.isSuccessful) {
                    goToUserProfile()
                    Toast.makeText(
                        requireContext(),
                        "Authentication created.",
                        Toast.LENGTH_SHORT,
                    ).show()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
    }

    private fun goToUserProfile() {
        findNavController().navigate(R.id.go_to_authFragment)
    }


}