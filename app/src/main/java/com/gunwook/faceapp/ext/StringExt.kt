package com.gunwook.faceapp.ext

import android.content.Context
import android.text.Editable
import android.widget.Toast
import com.gunwook.faceapp.utils.CodeUtils

fun String.isEmailValid(): Boolean {
    val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})";

    return EMAIL_REGEX.toRegex().matches(this);
}


fun String.setUrl() : String {
    return CodeUtils.Network.IMAGE_URL + this
}

fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)


fun String.toast(context : Context) {
    Toast.makeText(context, this, Toast.LENGTH_LONG).show()
}

fun String.isPasswordValid() : Boolean {
    var result = false

    if (8 <= this.length && this.length <= 20) result = true

    return result
}