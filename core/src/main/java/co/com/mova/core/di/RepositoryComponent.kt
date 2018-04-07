package co.com.mova.core.di

import co.com.mova.core.use_cases.genre.GetGenresUseCase
import co.com.mova.core.use_cases.genre.GetMovieGenresUseCase
import co.com.mova.core.use_cases.movie.GetFavoriteMoviesUseCase
import co.com.mova.core.use_cases.movie.GetMovieUseCase
import co.com.mova.core.use_cases.movie.GetMoviesUseCase
import co.com.mova.core.use_cases.movie.MakeMovieFavoriteUseCase
import dagger.Component
import javax.inject.Singleton

/**
 * Created by oscarg798 on 4/7/18.
 */
@Singleton
@Component(modules = [(RepositoryModule::class)])
interface RepositoryComponent {

    fun inject(getGenresUseCase: GetGenresUseCase)

    fun inject(getMovieGenresUseCase: GetMovieGenresUseCase)

    fun inject(getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase)

    fun inject(getMovieUseCase: GetMovieUseCase)

    fun inject(makeFavoriteMoviesUseCase: MakeMovieFavoriteUseCase)

    fun inject(getMoviesUseCase:GetMoviesUseCase)

}