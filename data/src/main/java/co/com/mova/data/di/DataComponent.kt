package co.com.mova.data.di

import co.com.mova.data.network.routes.IGenreRoute
import co.com.mova.data.network.routes.IMovieRoute
import co.com.mova.data.repositories.GenreRepository
import co.com.mova.data.repositories.MovieRepository
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by oscarg798 on 4/7/18.
 */
@Singleton
@Component(modules = [(RoutesModule::class)])
interface DataComponent {


    fun inject(genreRepository: GenreRepository)

    fun inject(movieRepository: MovieRepository)

    /**
     * we exposed some dependencies need it to unit testing
     */
    fun retrofit(): Retrofit

    fun genreRoute(): IGenreRoute

    fun movieRoute(): IMovieRoute
}