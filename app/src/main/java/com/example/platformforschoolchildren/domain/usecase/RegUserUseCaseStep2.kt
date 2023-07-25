package com.example.platformforschoolchildren.domain.usecase

import com.example.platformforschoolchildren.domain.entity.RegisterEntity
import com.example.platformforschoolchildren.domain.repository.RegRepository
import javax.inject.Inject

class RegUserUseCaseStep2 @Inject constructor(
    private val repo: RegRepository
) {
    operator fun invoke(reg: RegisterEntity) = repo.registerUserStep2(reg)
}