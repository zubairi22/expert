package com.bangkit.tmdb

import android.app.Application
import com.bangkit.tmdb.core.di.databaseModule
import com.bangkit.tmdb.core.di.networkModule
import com.bangkit.tmdb.core.di.repositoryModule
import com.bangkit.tmdb.di.useCaseModule
import com.bangkit.tmdb.di.viewModelModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

@FlowPreview
@ExperimentalCoroutinesApi
class MovieApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MovieApp)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}