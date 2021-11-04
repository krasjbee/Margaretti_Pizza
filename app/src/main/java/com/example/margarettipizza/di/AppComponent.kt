package com.example.margarettipizza.di

import com.example.margarettipizza.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Component(modules = [AndroidInjectionModule::class, ActivitiesModule::class, AppModule::class, FragmentModule::class, RepoModule::class])
@Singleton
interface AppComponent : AndroidInjector<Application> {

    override fun inject(instance: Application?)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun bindContext(application: Application): Builder

        fun build(): AppComponent

    }
}