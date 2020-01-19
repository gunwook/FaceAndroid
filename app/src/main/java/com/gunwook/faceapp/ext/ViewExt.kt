package com.gunwook.faceapp.ext

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    hideKeyboard(if (currentFocus == null) View(this) else currentFocus!!)
}

fun ViewGroup.inflate(resId: Int, attach: Boolean = false): View
        = LayoutInflater.from(context).inflate(resId, this, attach)

fun Activity.inflate(resId : Int, root : ViewGroup?) : View = LayoutInflater.from(this).inflate(resId,root)

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}