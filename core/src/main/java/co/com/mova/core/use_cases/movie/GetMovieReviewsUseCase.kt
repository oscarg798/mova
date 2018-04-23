package co.com.mova.core.use_cases.movie

import co.com.mova.core.entities.MovieReview
import co.com.mova.core.use_cases.base.SingleUseCase
import co.com.mova.data.local.entities.DBMovieReview
import co.com.mova.data.network.entities.APIMovieReview
import co.com.mova.data.repositories.IMovieRepository
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by oscarg798 on 4/23/18.
 */
class GetMovieReviewsUseCase(mSubscribeOnScheduler: Scheduler,
                             mObserverOnScheduler: Scheduler) :
        SingleUseCase<List<MovieReview>, Int>(mSubscribeOnScheduler, mObserverOnScheduler) {

    @Inject
    lateinit var mMovieRepository: IMovieRepository

    override fun buildUseCase(params: Int): Single<List<MovieReview>> {
        return Single.fromObservable(mMovieRepository.getMovieReviewsFromAPI(params))
                .doOnError {
                    mMovieRepository.getMovieReviewsFromDB(params)
                            .map {
                                APIMovieReview(it.id, it.author, it.content, it.url)
                            }
                }.map {
                    it.map {
                        mMovieRepository.insertMovieReview(DBMovieReview(it.id, it.author, it.content, it.url, params))
                        MovieReview(it.id, it.author, it.content, it.url)
                    }
                }
    }
}