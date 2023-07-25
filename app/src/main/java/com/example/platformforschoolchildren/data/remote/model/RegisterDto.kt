package com.example.platformforschoolchildren.data.remote.model

data class RegisterDto(
    val email: String? = null,
    val password: String? = null,
    val confirmPassword: String? = null,
    val schoolName: String? = null,
    val schoolNumber: String? = null,
    val schoolClass: String? = null,
    val userCityOrRegion: String? = null,
)
