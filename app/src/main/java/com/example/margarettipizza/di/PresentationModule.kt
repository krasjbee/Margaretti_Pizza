package com.example.margarettipizza.di

import com.example.margarettipizza.MainActivity
import com.example.view.cart.CartFragment
import com.example.view.details.DetailsDialog
import com.example.view.menu.MenuFragment
import com.example.view.preview.PreviewFragment
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

    @ContributesAndroidInjector
    abstract fun cartFragment(): CartFragment
}