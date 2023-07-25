package com.example.platformforschoolchildren.domain.usecase

import javax.inject.Inject
import com.example.platformforschoolchildren.domain.repository.RegRepository
import com.example.platformforschoolchildren.domain.entity.RegisterEntity

class RegisterUserUseCase@Inject constructor(
    private val repo: RegRepository) {

    operator fun invoke(reg: RegisterEntity) = repo.registerUser(reg)

}