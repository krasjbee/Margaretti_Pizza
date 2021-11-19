package com.example.margarettipizza.di

import com.example.margarettipizza.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Component(
    modules = [
        AndroidInjectionModule::class, AppModule::class, PresentationModule::class,
        NetworkModule::class, DatabaseModule::class, RepositoryModule::class, UsecaseModule::class]
)
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