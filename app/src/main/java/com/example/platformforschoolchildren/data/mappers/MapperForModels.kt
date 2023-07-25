package com.example.platformforschoolchildren.data.mappers

import retrofit2.Response
import com.example.platformforschoolchildren.data.remote.model.RegisterDto
import com.example.platformforschoolchildren.domain.entity.RegisterEntity

class MapperForModels {

    fun mapEntityToDbModel(reg: RegisterEntity?) = RegisterDto(
        email = reg?.email,
        password = reg?.password,
        confirmPassword = reg?.confirmPassword,
        schoolName = reg?.schoolName,
        schoolNumber = reg?.schoolNumber,
        schoolClass = reg?.schoolClass,
        userCityOrRegion = reg?.userCityOrRegion,
    )

    fun mapRespDbModelToRespEntity(list: Response<RegisterDto?>) = if (list.isSuccessful) {
        Response.success(mapDbModelToEntity(list.body()))
    } else {
        list.errorBody()?.let { Response.error(list.code(), it) }
    }

    private fun mapDbModelToEntity(regDto: RegisterDto?) = RegisterEntity(
        email = regDto?.email,
        password = regDto?.password,
        confirmPassword = regDto?.confirmPassword,
        schoolName = regDto?.schoolName,
        schoolNumber = regDto?.schoolNumber,
        schoolClass = regDto?.schoolClass,
        userCityOrRegion = regDto?.userCityOrRegion,
    )

}