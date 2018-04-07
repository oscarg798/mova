package co.com.mova.core.use_cases

import co.com.mova.core.di.RepositoryComponent
import co.com.mova.core.entities.Genre
import co.com.mova.core.entities.Movie
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
            SingleUseCase<Pair<Boolean, List<Movie>>, Int>

    fun getMovieUseCase(subscribeScheduler: Scheduler,
                        observerScheduler: Scheduler):
            SingleUseCase<Movie, Int>

    fun getMakeMovieFavoriteUseCase(subscribeScheduler: Scheduler,
                                    observerScheduler: Scheduler):
            CompletableUseCase<Int>
}