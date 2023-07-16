package com.example.platformforschoolchildren.presentation.fragments

import android.view.LayoutInflater
import com.example.platformforschoolchildren.core.base.BaseFragment
import com.example.platformforschoolchildren.databinding.FragmentAuthBinding


class AuthFragment : BaseFragment<FragmentAuthBinding>() {
    override fun inflateViewBinding(inflater: LayoutInflater) =
        FragmentAuthBinding.inflate(inflater)



    //   --------------------------------

//    private lateinit var mAuth : FirebaseAuth
//    public override fun onStart() {
//        super.onStart()
//
//        val currentUser = mAuth.currentUser
//        if (currentUser != null) {
//            findNavController().navigate(R.id.go_to_mainPageFragment)
//        }
//    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        init()
//        binding.register.setOnClickListener {
//
//            val email = binding.email.text.toString()
//            val password = binding.password.text.toString()
//            if (TextUtils.isEmpty(email)) {
//                Toast.makeText(
//                    requireContext(),
//                    "введите почту",
//                    Toast.LENGTH_SHORT,
//                ).show()
//                return@setOnClickListener
//            }
//            if (TextUtils.isEmpty(password)) {
//                Toast.makeText(
//                    requireContext(),
//                    "введите пароль",
//                    Toast.LENGTH_SHORT,
//                ).show()
//                return@setOnClickListener
//            }
//
//            enter(email, password)
//            binding.progressBar.visibility = View.VISIBLE
//        }
//    }

//    private fun init() {
//        mAuth = Firebase.auth
//    }


//    private fun enter(email: String, password: String) {
//
//        mAuth.signInWithEmailAndPassword(email, password)
//            .addOnCompleteListener(requireActivity()) { task ->
//                binding.progressBar.visibility = View.GONE
//                if (task.isSuccessful) {
//                    Toast.makeText(
//                        requireContext(),
//                        "Authentication successful.",
//                        Toast.LENGTH_SHORT,
//                    ).show()
//                    findNavController().navigate(R.id.go_to_mainPageFragment)
//                } else {
//                    Toast.makeText(
//                        requireContext(),
//                        "Authentication failed.",
//                        Toast.LENGTH_SHORT,
//                    ).show()
//
//                }
//            }
//
//    }

}