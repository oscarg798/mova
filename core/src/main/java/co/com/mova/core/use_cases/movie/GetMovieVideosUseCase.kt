package co.com.mova.core.use_cases.movie

import co.com.mova.core.use_cases.base.SingleUseCase
import co.com.mova.data.repositories.IMovieRepository
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by oscarg798 on 4/7/18.
 */
class GetMovieVideosUseCase(mSubscribeOnScheduler: Scheduler,
                            mObserverOnScheduler: Scheduler) :
        SingleUseCase<String, Int>(mSubscribeOnScheduler, mObserverOnScheduler) {

    @Inject
    lateinit var mMovieRepository: IMovieRepository

    override fun buildUseCase(params: Int): Single<String> {
        return Single.fromObservable(mMovieRepository.getMovieVideos(params))
                .map {
                    val apiMovie = it.filter {
                        it.type.toLowerCase() == "trailer"

                    }.take(1)
                    if (apiMovie.isNotEmpty()) {
                        apiMovie[0].key
                    } else {
                        ""
                    }
                }

    }
}