package co.com.mova.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by oscarg798 on 4/7/18.
 */
@Singleton
@Module
class AppModule(private val mApp: Application) {

    @Provides
    @Singleton
    fun providesContext(): Context = mApp

}