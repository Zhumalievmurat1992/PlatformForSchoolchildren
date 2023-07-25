package com.example.platformforschoolchildren.domain.entity

import com.google.gson.annotations.SerializedName

data class RegisterEntity(
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("password")
    val password: String? = null,
    @SerializedName("confirmPassword")
    val confirmPassword: String? = null,
    @SerializedName("schoolName")
    val schoolName: String? = null,
    @SerializedName("schoolNumber")
    val schoolNumber: String? = null,
    @SerializedName("schoolClass")
    val schoolClass: String? = null,
    @SerializedName("userCityOrRegion")
    val userCityOrRegion: String? = null,
)
