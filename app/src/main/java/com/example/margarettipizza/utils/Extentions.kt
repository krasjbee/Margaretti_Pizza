package com.example.margarettipizza.utils

import androidx.fragment.app.FragmentManager

fun FragmentManager.clearBackStack() {
    for (i in 0..this.backStackEntryCount) {
        this.popBackStack()
    }
}