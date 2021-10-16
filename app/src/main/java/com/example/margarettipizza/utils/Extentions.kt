package com.example.margarettipizza.utils

import androidx.fragment.app.FragmentManager

fun FragmentManager.clearAllBackStack() =
    popBackStack(getBackStackEntryAt(0).id, FragmentManager.POP_BACK_STACK_INCLUSIVE)