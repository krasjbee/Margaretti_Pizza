package com.example.margarettipizza.di

import com.example.margarettipizza.MainActivity
import com.example.margarettipizza.presentation.menu.MenuFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class PresentationModule {

    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun menuFragment(): MenuFragment

}