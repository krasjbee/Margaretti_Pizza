package com.example.view.utils

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

fun FragmentManager.clearAllBackStack() =
    popBackStack(getBackStackEntryAt(0).id, FragmentManager.POP_BACK_STACK_INCLUSIVE)

fun Fragment.hideKeyboard() {
    val inputMethodManager =
        this.context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(this.view?.windowToken, 0)
}