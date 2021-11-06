package com.example.margarettipizza.di

import com.example.margarettipizza.MainActivity
import com.example.margarettipizza.presentation.details.DetailsDialog
import com.example.margarettipizza.presentation.menu.MenuFragment
import com.example.margarettipizza.presentation.preview.PreviewFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class PresentationModule {

    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun menuFragment(): MenuFragment

    @ContributesAndroidInjector
    abstract fun detailsFragment(): DetailsDialog

    @ContributesAndroidInjector
    abstract fun previewFragment(): PreviewFragment
}