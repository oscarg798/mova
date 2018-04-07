package co.com.mova.core.use_cases.movie

import co.com.mova.core.use_cases.base.CompletableUseCase
import co.com.mova.data.repositories.IMovieRepository
import io.reactivex.Completable
import io.reactivex.Scheduler
import javax.inject.Inject

/**
 * Created by oscarg798 on 4/7/18.
 */
class MakeMovieFavoriteUseCase(mSubscribeOnScheduler: Scheduler,
                               mObserverOnScheduler: Scheduler) :
        CompletableUseCase<Int>(mSubscribeOnScheduler, mObserverOnScheduler) {

    @Inject
    lateinit var mMovieRepository: IMovieRepository

    override fun buildUseCase(params: Int): Completable {
        return Completable.create {
            mMovieRepository.makeMovieFavorite(params)
            it.onComplete()
        }
    }
}