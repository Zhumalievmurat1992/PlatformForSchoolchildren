package com.example.platformforschoolchildren.utils

interface InputHelper {

    fun passwordWatcher(notEmpty: Boolean, fieldsNumber: Int)

    fun textWatcher(notEmpty: Boolean,text: Int)
}