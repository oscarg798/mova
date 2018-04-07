package co.com.mova.di

import co.com.mova.detail.IMovieDetailActivityPresenter
import co.com.mova.detail.MovieDetailActivityPresenter
import co.com.mova.movies.MoviesActivityPresenter
import co.com.mova.splash.SplashActivityPresenter
import dagger.Component
import javax.inject.Singleton

/**
 * Created by oscarg798 on 4/7/18.
 */
@Singleton
@Component(modules = [(AppModule::class), (UseCaseModule::class)])
interface AppComponent {

    fun inject(splashActivityPresenter: SplashActivityPresenter)

    fun inject(moviesActivityPresenter: MoviesActivityPresenter)

    fun inject(moviesDetailActivityPresenter: MovieDetailActivityPresenter)
}