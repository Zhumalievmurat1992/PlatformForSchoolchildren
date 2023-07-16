package com.example.platformforschoolchildren.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.platformforschoolchildren.R
import com.example.platformforschoolchildren.databinding.FragmentMainPageBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainPageFragment : Fragment() {

    private lateinit var binding: FragmentMainPageBinding
    private lateinit var mAuth : FirebaseAuth
    private var user: FirebaseUser? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainPageBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        mAuth = Firebase.auth
        user = mAuth.currentUser
        if (user == null) {
            findNavController().navigate(R.id.action_mainPageFragment_to_authFragment)
        } else {
            binding.txt.text = user?.email.toString()
        }

        binding.btnGoToAuthFragment.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            findNavController().navigate(R.id.action_mainPageFragment_to_authFragment)
        }

    }

}