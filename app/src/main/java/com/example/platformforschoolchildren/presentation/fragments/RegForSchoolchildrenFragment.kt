package com.example.platformforschoolchildren.presentation.fragments

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.platformforschoolchildren.R
import com.example.core.base.BaseFragment
import  com.example.platformforschoolchildren.presentation.fragments.viewmodel.RegistrationViewModel
import com.example.platformforschoolchildren.databinding.FragmentRegForSchoolchildrenBinding
import com.example.platformforschoolchildren.domain.entity.RegisterEntity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegForSchoolchildrenFragment :
    BaseFragment<FragmentRegForSchoolchildrenBinding, RegistrationViewModel>() {

    override val viewModel: RegistrationViewModel by lazy {
        ViewModelProvider(this)[RegistrationViewModel::class.java]
    }

    override fun inflateViewBinding(inflater: LayoutInflater) =
        FragmentRegForSchoolchildrenBinding.inflate(inflater)

    override fun initListener() {
        super.initListener()

        binding.btnReg.setOnClickListener {

            viewModel.regUser2(RegisterEntity(
                schoolName = binding.nameSchoolEdText.text.toString(),
                schoolNumber = binding.numSchoolEdText.text.toString(),
                schoolClass = binding.classEdText.text.toString(),
                userCityOrRegion = binding.areaEdText.text.toString(),
            ))
            findNavController().navigate(R.id.personalAccountFragment)
        }

        binding.accountEnter.setOnClickListener {
            findNavController().navigate(R.id.authFragment)
        }

        binding.numSchoolEdText.addTextChangedListener(textWatcher)
        binding.classEdText.addTextChangedListener(textWatcher)
        binding.areaEdText.addTextChangedListener(textWatcher)

    }

    private val textWatcher = object : TextWatcher {

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            val numberSchool = binding.numSchoolEdText.text.toString().trim()
            val klass = binding.classEdText.text.toString().trim()
            val area = binding.areaEdText.text.toString().trim()

            binding.btnReg.isEnabled =
                numberSchool.isNotEmpty() && klass.isNotEmpty() && area.isNotEmpty()
        }

        override fun afterTextChanged(p0: Editable?) {}

    }
}