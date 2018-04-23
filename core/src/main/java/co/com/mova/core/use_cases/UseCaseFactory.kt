package co.com.mova.core.use_cases

import android.content.Context
import co.com.mova.core.di.DaggerRepositoryComponent
import co.com.mova.core.di.RepositoryComponent
import co.com.mova.core.di.RepositoryModule
import co.com.mova.core.entities.Genre
import co.com.mova.core.entities.Movie
import co.com.mova.core.entities.MovieCast
import co.com.mova.core.entities.MovieReview
import co.com.mova.core.use_cases.base.CompletableUseCase
import co.com.mova.core.use_cases.base.SingleUseCase
import co.com.mova.core.use_cases.genre.GetGenresUseCase
import co.com.mova.core.use_cases.genre.GetMovieGenresUseCase
import co.com.mova.core.use_cases.movie.*
import io.reactivex.Scheduler

/**
 * Created by oscarg798 on 4/7/18.
 */
class UseCaseFactory(mContext: Context) : IUseCaseFactory {

    override val mRepositoryComponent: RepositoryComponent? = DaggerRepositoryComponent.builder()
            .repositoryModule(RepositoryModule(mContext))
            .build()


    override fun getGenresUseCase(subscribeScheduler: Scheduler,
                                  observerScheduler: Scheduler):
            SingleUseCase<List<Genre>, Any?> {
        val useCase = GetGenresUseCase(subscribeScheduler, observerScheduler)
        mRepositoryComponent?.inject(useCase)
        return useCase

    }

    override fun getMovieGenresUseCase(subscribeScheduler: Scheduler,
                                       observerScheduler: Scheduler):
            SingleUseCase<List<Genre>, List<Int>> {

        val useCase = GetMovieGenresUseCase(subscribeScheduler, observerScheduler)
        mRepositoryComponent?.inject(useCase)
        return useCase
    }

    override fun getFavoriteMoviesUseCase(subscribeScheduler: Scheduler,
                                          observerScheduler: Scheduler):
            SingleUseCase<List<Movie>, Any?> {
        val useCase = GetFavoriteMoviesUseCase(subscribeScheduler, observerScheduler)
        mRepositoryComponent?.inject(useCase)
        return useCase
    }

    override fun getMoviesUseCase(subscribeScheduler: Scheduler,
                                  observerScheduler: Scheduler):
            SingleUseCase<Pair<Boolean, List<Movie>>, Int> {
        val useCase = GetMoviesUseCase(subscribeScheduler, observerScheduler)
        mRepositoryComponent?.inject(useCase)
        return useCase
    }

    override fun getMovieUseCase(subscribeScheduler: Scheduler,
                                 observerScheduler: Scheduler):
            SingleUseCase<Movie, Int> {
        val useCase = GetMovieUseCase(subscribeScheduler, observerScheduler)
        mRepositoryComponent?.inject(useCase)
        return useCase
    }

    override fun getMakeMovieFavoriteUseCase(subscribeScheduler: Scheduler,
                                             observerScheduler: Scheduler):
            CompletableUseCase<Pair<Int, Boolean>> {
        val useCase = ToggleMovieFavoriteValue(subscribeScheduler, observerScheduler)
        mRepositoryComponent?.inject(useCase)
        return useCase
    }


    override fun getMovieVideosUseCase(subscribeScheduler: Scheduler, observerScheduler: Scheduler): SingleUseCase<String, Int> {
        val useCase = GetMovieVideosUseCase(subscribeScheduler, observerScheduler)
        mRepositoryComponent?.inject(useCase)
        return useCase
    }

    override fun getMovieCastUseCase(subscribeScheduler: Scheduler, observerScheduler: Scheduler): SingleUseCase<List<MovieCast>, Int> {
        val useCase = GetMovieCastUseCase(subscribeScheduler, observerScheduler)
        mRepositoryComponent?.inject(useCase)
        return useCase
    }

    override fun getMovieReviewsUseCase(subscribeScheduler: Scheduler, observerScheduler: Scheduler): SingleUseCase<List<MovieReview>, Int> {
        val useCase = GetMovieReviewsUseCase(subscribeScheduler, observerScheduler)
        mRepositoryComponent?.inject(useCase)
        return useCase
    }

}