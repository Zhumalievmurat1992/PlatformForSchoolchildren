package com.example.platformforschoolchildren.presentation.fragments.viewmodel

import androidx.lifecycle.viewModelScope
import  com.example.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.platformforschoolchildren.domain.usecase.RegisterUserUseCase
import com.example.platformforschoolchildren.domain.usecase.RegUserUseCaseStep2
import kg.o.internlabs.core.common.ApiState
import kotlinx.coroutines.flow.MutableStateFlow
import com.example.platformforschoolchildren.domain.entity.RegisterEntity
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase,
    private val regUserUseCaseStep2: RegUserUseCaseStep2,
) : BaseViewModel() {

    private val _registerUser = MutableStateFlow<ApiState<RegisterEntity>>(ApiState.Loading)
    val registerUser = _registerUser.asStateFlow()

    private val _regUser2 = MutableStateFlow<ApiState<RegisterEntity>>(ApiState.Loading)
    val regUser2 = _regUser2.asStateFlow()

    fun registerUser(reg: RegisterEntity) {
        viewModelScope.launch {
            registerUserUseCase(reg).collectLatest {
                when (it) {
                    is ApiState.Success -> {
                        _registerUser.value = it
                    }
                    is ApiState.Failure -> {
                        _registerUser.value = it
                    }
                    ApiState.Loading -> {
                    }
                }
            }
        }
    }

    fun regUser2(reg: RegisterEntity) {
        viewModelScope.launch {
            regUserUseCaseStep2(reg).collectLatest {
                when (it) {
                    is ApiState.Success -> {
                        _regUser2.value = it
                    }
                    is ApiState.Failure -> {
                        _regUser2.value = it
                    }
                    ApiState.Loading -> {
                    }
                }
            }
        }
    }

}