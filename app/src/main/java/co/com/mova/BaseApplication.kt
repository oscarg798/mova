package co.com.mova

import android.app.Application
import co.com.mova.di.AppModule
import co.com.mova.di.DaggerAppComponent
import co.com.mova.di.UseCaseModule

/**
 * Created by oscarg798 on 4/7/18.
 */
class BaseApplication : Application() {

    val appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .useCaseModule(UseCaseModule())
            .build()



}