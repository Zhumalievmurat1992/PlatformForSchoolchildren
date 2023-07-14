package com.example.platformforschoolchildren

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.platformforschoolchildren.databinding.FragmentRegistrationBinding
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.example.platformforschoolchildren.utils.InputHelper

class RegistrationFragment : Fragment() {

    private lateinit var binding: FragmentRegistrationBinding

    private lateinit var mAuth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

//    override fun onStart() {
//        super.onStart()
//
//        val currentUser = mAuth.currentUser
//        if (currentUser != null) {
//            findNavController().navigate(R.id.go_to_authFragment)
//        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        emailFocusListener()

        binding.accountEnter.setOnClickListener {
            findNavController().navigate(R.id.authFragment)
        }
        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.regForSchoolchildrenFragment)
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
            binding.btnNext.isEnabled = userName.isNotEmpty() && lastName.isNotEmpty() && password.isNotEmpty()
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


//        binding.register.setOnClickListener {
//
//            val email = binding.email.text.toString()
//            val password = binding.password.text.toString()
//
//            if (TextUtils.isEmpty(email)) {
//                Toast.makeText(
//                    requireContext(),
//                    "введите почту",
//                    Toast.LENGTH_SHORT,
//                ).show()
//                return@setOnClickListener
//            }
//
//            if (TextUtils.isEmpty(password)) {
//                Toast.makeText(
//                    requireContext(),
//                    "введите пароль",
//                    Toast.LENGTH_SHORT,
//                ).show()
//                return@setOnClickListener
//            }
//
//            createUser(email, password)
//            binding.progressBar.visibility = View.VISIBLE
//        }
//
//        binding.googleBtn.setOnClickListener {
//            signInWithGoogle()
//        }
//
//        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken(getString(R.string.default_web_client_id))
//            .requestEmail()
//            .build()
//        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)


//    private fun init() {
//        mAuth = Firebase.auth
//    }

//    private fun createUser(email: String, password: String) {
//
//        mAuth.createUserWithEmailAndPassword(email, password)
//            .addOnCompleteListener(requireActivity()) { task ->
//                binding.progressBar.visibility = View.GONE
//                if (task.isSuccessful) {
//                    goToUserProfile()
//                    Toast.makeText(
//                        requireContext(),
//                        "Authentication created.",
//                        Toast.LENGTH_SHORT,
//                    ).show()
//                } else {
//                    Toast.makeText(
//                        requireContext(),
//                        "Authentication failed.",
//                        Toast.LENGTH_SHORT,
//                    ).show()
//                }
//            }
//    }

//
//    private fun signInWithGoogle() {
//        val signInIntent = googleSignInClient.signInIntent
//        launcher.launch(signInIntent)
//    }
//
//    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
//    { result ->
//        if (result.resultCode == Activity.RESULT_OK) {
//            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
//            handleResults(task)
//        }
//    }

//    private fun handleResults(task: Task<GoogleSignInAccount>) {
//        if (task.isSuccessful) {
//            val account: GoogleSignInAccount? = task.result
//            if (account!= null) {
//                updateUI(account)
//            }
//        }
//        else {
//            Toast.makeText(requireContext(), "не удалось войти в систему", Toast.LENGTH_SHORT).show()
//        }
//    }

//    private fun updateUI(account: GoogleSignInAccount) {
//        binding.progressBar.visibility = View.VISIBLE
//        val credential = GoogleAuthProvider.getCredential(account.idToken,null)
//        mAuth.signInWithCredential(credential).addOnCompleteListener{
//            if (it.isSuccessful) {
//                binding.progressBar.visibility = View.GONE
//                findNavController().navigate(R.id.mainPageFragment)
//            }
//            else {
//                binding.progressBar.visibility = View.GONE
//                Toast.makeText(requireContext(), "в данный момент не удается войти в систему", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }


}

