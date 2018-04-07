package co.com.mova.data.di

import co.com.mova.data.network.routes.IGenreRoute
import co.com.mova.data.network.routes.IMovieRoute
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by oscarg798 on 4/7/18.
 */
@Singleton
@Module(includes = [(NetworkModule::class), (DatabaseModule::class)])
class RoutesModule {

    @Provides
    fun providesGenreRoute(retrofit: Retrofit): IGenreRoute = retrofit.create(IGenreRoute::class.java)

    @Provides
    fun providesMovieRoute(retrofit: Retrofit): IMovieRoute = retrofit.create(IMovieRoute::class.java)

}
