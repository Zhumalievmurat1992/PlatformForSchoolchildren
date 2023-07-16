package com.example.platformforschoolchildren.presentation.fragments

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import androidx.navigation.fragment.findNavController
import com.example.platformforschoolchildren.R
import com.example.platformforschoolchildren.core.base.BaseFragment
import com.example.platformforschoolchildren.databinding.FragmentRegForSchoolchildrenBinding

class RegForSchoolchildrenFragment : BaseFragment<FragmentRegForSchoolchildrenBinding>() {
    override fun inflateViewBinding(inflater: LayoutInflater) =
        FragmentRegForSchoolchildrenBinding.inflate(inflater)

    override fun initView() {
        super.initView()
    }

    override fun initListener() {
        super.initListener()

        binding.btnReg.setOnClickListener {
            findNavController().navigate(R.id.personalAccountFragment) }

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