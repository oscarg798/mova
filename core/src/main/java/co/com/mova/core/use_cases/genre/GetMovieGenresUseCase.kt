package co.com.mova.core.use_cases.genre

import co.com.mova.core.entities.Genre
import co.com.mova.core.use_cases.base.SingleUseCase
import co.com.mova.data.repositories.IGenreRepository
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by oscarg798 on 4/7/18.
 */
class GetMovieGenresUseCase(mSubscribeOnScheduler: Scheduler,
                            mObserverOnScheduler: Scheduler) :
        SingleUseCase<List<Genre>, List<Int>>(mSubscribeOnScheduler, mObserverOnScheduler) {

    @Inject
    lateinit var mGenreRepository: IGenreRepository

    override fun buildUseCase(params: List<Int>): Single<List<Genre>> {
        return Single.create { emitter ->
            val genres = ArrayList<Genre>()
            params.forEach {
                mGenreRepository.getGenre(it)?.let {
                    genres.add(Genre(it.id, it.name))
                }


                emitter.onSuccess(genres)


            }
        }
    }
}