package com.example.margarettipizza.di

import com.example.margarettipizza.presentation.menu.MenuFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun menuFragment(): MenuFragment

}