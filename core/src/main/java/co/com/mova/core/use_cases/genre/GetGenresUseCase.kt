package co.com.mova.core.use_cases.genre

import co.com.mova.core.entities.Genre
import co.com.mova.core.use_cases.base.SingleUseCase
import co.com.mova.data.local.entities.DBGenre
import co.com.mova.data.network.entities.APIGenre
import co.com.mova.data.repositories.IGenreRepository
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by oscarg798 on 4/7/18.
 */

class GetGenresUseCase(mSubscribeOnScheduler: Scheduler,
                       mObserverOnScheduler: Scheduler) :
        SingleUseCase<List<Genre>, Any?>(mSubscribeOnScheduler, mObserverOnScheduler) {


    @Inject
    lateinit var mGenreRepository: IGenreRepository


    override fun buildUseCase(params: Any?): Single<List<Genre>> {
        return Single.fromObservable(mGenreRepository.getGenresFromAPI())
                .onErrorReturn {
                    mGenreRepository.getGenres().map {
                        APIGenre(it.id, it.name)
                    }
                }
                .map {
                    it.map {
                        mGenreRepository.insertGenre(DBGenre(it.id, it.name))
                        Genre(it.id, it.name)
                    }

                }
    }
}
