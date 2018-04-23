package co.com.mova.di

import co.com.mova.detail.IMovieDetailActivityPresenter
import co.com.mova.detail.MovieDetailActivityPresenter
import co.com.mova.detail.cast.CastFragmentPresenter
import co.com.mova.detail.info.MovieInfoFragmentPresenter
import co.com.mova.detail.review.ReviewFragmentPresenter
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

    fun inject(movieInfoFragmentPresenter: MovieInfoFragmentPresenter)

    fun inject(castFragmentPresenter: CastFragmentPresenter)

    fun inject(reviewFragmentPresenter: ReviewFragmentPresenter)
}