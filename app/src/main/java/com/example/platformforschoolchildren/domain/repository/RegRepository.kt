package com.example.platformforschoolchildren.domain.repository

import kg.o.internlabs.core.common.ApiState
import kotlinx.coroutines.flow.Flow
import com.example.platformforschoolchildren.domain.entity.RegisterEntity

interface RegRepository {

    fun registerUser(reg: RegisterEntity): Flow<ApiState<RegisterEntity>>
    fun registerUserStep2(reg: RegisterEntity): Flow<ApiState<RegisterEntity>>

}