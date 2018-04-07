package co.com.mova.core.use_cases.movie

import co.com.mova.core.use_cases.base.CompletableUseCase
import co.com.mova.data.repositories.IMovieRepository
import io.reactivex.Completable
import io.reactivex.Scheduler
import javax.inject.Inject

/**
 * Created by oscarg798 on 4/7/18.
 */
class ToggleMovieFavoriteValue(mSubscribeOnScheduler: Scheduler,
                               mObserverOnScheduler: Scheduler) :
        CompletableUseCase<Pair<Int, Boolean>>(mSubscribeOnScheduler, mObserverOnScheduler) {

    @Inject
    lateinit var mMovieRepository: IMovieRepository

    override fun buildUseCase(params: Pair<Int, Boolean>): Completable {
        return Completable.create {
            if (params.second) {
                mMovieRepository.makeMovieFavorite(params.first)
            } else {
                mMovieRepository.removeMovieFromFavorites(params.first)
            }

            it.onComplete()
        }
    }
}