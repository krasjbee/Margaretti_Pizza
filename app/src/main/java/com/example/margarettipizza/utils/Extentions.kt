package com.example.margarettipizza.utils

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import kotlin.properties.Delegates

fun FragmentManager.clearBackStack() {
    for (i in 0..this.backStackEntryCount) {
        this.popBackStack()
    }
}

//this is something stupid, but i have no any other ideas for now ¯\_(ツ)_/¯
private var mainContainer: Int by Delegates.notNull()

fun AppCompatActivity.navigateTo(
    destination: Fragment,
    @IdRes container: Int = mainContainer,
    isInBackStack: Boolean = true,
    backStackName: String? = null,
    tag: String? = null,
) {
    mainContainer = container
    prepareTransaction(
        mainContainer,
        destination,
        isInBackStack,
        backStackName,
        tag,
        supportFragmentManager
    ).commit()
}

fun Fragment.navigateTo(
    destination: Fragment,
    @IdRes container: Int = mainContainer,
    isInBackStack: Boolean = true,
    backStackName: String? = null,
    tag: String? = null,
) {
    prepareTransaction(
        container,
        destination,
        isInBackStack,
        backStackName,
        tag,
        parentFragmentManager
    ).commit()
}

private fun prepareTransaction(
    @IdRes container: Int,
    destination: Fragment,
    isInBackStack: Boolean,
    backStackName: String?,
    tag: String?,
    fragmentManager: FragmentManager
): FragmentTransaction {
    val transaction = fragmentManager.beginTransaction()
        .replace(container, destination, tag)
    if (isInBackStack) {
        transaction.addToBackStack(backStackName)
    }
    return transaction
}


