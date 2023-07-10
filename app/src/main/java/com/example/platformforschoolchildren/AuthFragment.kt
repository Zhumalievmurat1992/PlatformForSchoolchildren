package com.example.platformforschoolchildren

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.platformforschoolchildren.databinding.FragmentAuthBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class AuthFragment : Fragment() {

    private lateinit var binding: FragmentAuthBinding
    private lateinit var mAuth : FirebaseAuth

    public override fun onStart() {
        super.onStart()

        val currentUser = mAuth.currentUser
        if (currentUser != null) {
            findNavController().navigate(R.id.go_to_mainPageFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthBinding.inflate(inflater,container,false)
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

            enter(email,password)
            binding.progressBar.visibility = View.VISIBLE
        }
    }

    private fun init() {
        mAuth = Firebase.auth
    }


    private fun enter(email: String, password: String) {

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                binding.progressBar.visibility = View.GONE
                if (task.isSuccessful) {
                    Toast.makeText(
                        requireContext(),
                        "Authentication successful.",
                        Toast.LENGTH_SHORT,
                    ).show()
                    findNavController().navigate(R.id.go_to_mainPageFragment)
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()

                }
            }

    }

}