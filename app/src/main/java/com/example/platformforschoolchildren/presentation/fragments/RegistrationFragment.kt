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


//    private lateinit var mAuth: FirebaseAuth
//    private lateinit var googleSignInClient: GoogleSignInClient
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


//   --------------------------------

    //    override fun onStart() {
//        super.onStart()
//
//        val currentUser = mAuth.currentUser
//        if (currentUser != null) {
//            findNavController().navigate(R.id.go_to_authFragment)
//        }
//    }

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

