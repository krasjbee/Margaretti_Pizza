package com.example.margarettipizza.di

import com.example.margarettipizza.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesModule {
    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity

}