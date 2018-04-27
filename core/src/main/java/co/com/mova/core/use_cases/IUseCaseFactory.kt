package co.com.mova.core.use_cases

import co.com.mova.core.di.RepositoryComponent
import co.com.mova.core.entities.Genre
import co.com.mova.core.entities.Movie
import co.com.mova.core.entities.MovieCast
import co.com.mova.core.entities.MovieReview
import co.com.mova.core.use_cases.base.CompletableUseCase
import co.com.mova.core.use_cases.base.SingleUseCase
import io.reactivex.Scheduler

/**
 * Created by oscarg798 on 4/7/18.
 */
interface IUseCaseFactory {
    val mRepositoryComponent: RepositoryComponent?

    fun getGenresUseCase(subscribeScheduler: Scheduler,
                         observerScheduler: Scheduler):
            SingleUseCase<List<Genre>, Any?>

    fun getMovieGenresUseCase(subscribeScheduler: Scheduler,
                              observerScheduler: Scheduler):
            SingleUseCase<List<Genre>, List<Int>>

    fun getFavoriteMoviesUseCase(subscribeScheduler: Scheduler,
                                 observerScheduler: Scheduler):
            SingleUseCase<List<Movie>, Any?>

    fun getMoviesUseCase(subscribeScheduler: Scheduler,
                         observerScheduler: Scheduler):
            SingleUseCase<Pair<Boolean, List<Movie>>, Pair<Int, Int>>

    fun getMovieUseCase(subscribeScheduler: Scheduler,
                        observerScheduler: Scheduler):
            SingleUseCase<Movie, Int>

    fun getMakeMovieFavoriteUseCase(subscribeScheduler: Scheduler,
                                    observerScheduler: Scheduler):
            CompletableUseCase<Pair<Int,Boolean>>

    fun getMovieVideosUseCase(subscribeScheduler: Scheduler,
                              observerScheduler: Scheduler):
            SingleUseCase<String, Int>

    fun getMovieReviewsUseCase(subscribeScheduler: Scheduler,
                               observerScheduler: Scheduler):SingleUseCase<List<MovieReview>, Int>

    fun getMovieCastUseCase(subscribeScheduler: Scheduler,
                            observerScheduler: Scheduler):SingleUseCase<List<MovieCast>, Int>
}