package com.example.platformforschoolchildren.presentation.fragments

import android.app.Activity
import android.text.TextUtils
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.example.platformforschoolchildren.core.base.BaseFragment
import com.example.platformforschoolchildren.databinding.FragmentAuthBinding
import com.google.firebase.auth.FirebaseAuth
import com.example.platformforschoolchildren.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class AuthFragment : BaseFragment<FragmentAuthBinding>() {

    private lateinit var mAuth : FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    override fun inflateViewBinding(inflater: LayoutInflater) =
        FragmentAuthBinding.inflate(inflater)

    override fun onStart() {
        super.onStart()

        val currentUser = mAuth.currentUser
        if (currentUser != null) {
            findNavController().navigate(R.id.mainPageFragment)
        }
    }

    override fun initListener() {
        super.initListener()
        mAuth = Firebase.auth
        emailFocusListener()

        binding.btnEnter.setOnClickListener {
            val password = binding.password.text.toString()
            if (TextUtils.isEmpty(password)) {
                Toast.makeText(
                    requireContext(),
                    "введите пароль",
                    Toast.LENGTH_SHORT,
                ).show()
                return@setOnClickListener
            }
        }

        binding.btnGoogle.setOnClickListener {
            signInWithGoogle()
        }

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)


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

    private fun signInWithGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        launcher.launch(signInIntent)
    }

        private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
    { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            handleResults(task)
        }
    }

        private fun handleResults(task: Task<GoogleSignInAccount>) {
        if (task.isSuccessful) {
            val account: GoogleSignInAccount? = task.result
            if (account!= null) {
                updateUI(account)
            }
        }
        else {
            Toast.makeText(requireContext(), "не удалось войти в систему", Toast.LENGTH_SHORT).show()
        }
    }

        private fun updateUI(account: GoogleSignInAccount) {
        binding.progressBar.visibility = View.VISIBLE
        val credential = GoogleAuthProvider.getCredential(account.idToken,null)
        mAuth.signInWithCredential(credential).addOnCompleteListener{
            if (it.isSuccessful) {
                binding.progressBar.visibility = View.GONE
                findNavController().navigate(R.id.mainPageFragment)
            }
            else {
                binding.progressBar.visibility = View.GONE
                Toast.makeText(requireContext(), "в данный момент не удается войти в систему", Toast.LENGTH_SHORT).show()
            }
        }
    }



    //   --------------------------------


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