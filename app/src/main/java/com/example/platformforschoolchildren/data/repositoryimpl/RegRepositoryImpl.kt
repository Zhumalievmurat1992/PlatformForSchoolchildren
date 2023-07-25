package com.example.platformforschoolchildren.data.repositoryimpl

import com.example.core.base.BaseRepository
import com.example.platformforschoolchildren.data.mappers.MapperForModels
import com.example.platformforschoolchildren.data.remote.ApiService
import com.example.platformforschoolchildren.domain.entity.RegisterEntity
import com.example.platformforschoolchildren.domain.repository.RegRepository
import javax.inject.Inject


class RegRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : BaseRepository(), RegRepository {

    private val mapper = MapperForModels()

    override fun registerUser(reg: RegisterEntity) = safeApiCall {

        mapper.mapRespDbModelToRespEntity(
            apiService.registerUser(
                mapper.mapEntityToDbModel(
                    reg
                )
            )
        )
    }

    override fun registerUserStep2(reg: RegisterEntity) = safeApiCall {

        mapper.mapRespDbModelToRespEntity(
            apiService.registerUser2Step(
                mapper.mapEntityToDbModel(
                    reg
                )
            )
        )

    }

}