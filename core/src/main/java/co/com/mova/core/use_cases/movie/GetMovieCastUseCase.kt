package co.com.mova.core.use_cases.movie

import co.com.mova.core.entities.MovieCast
import co.com.mova.core.use_cases.base.SingleUseCase
import co.com.mova.data.local.entities.DBMovieCast
import co.com.mova.data.network.entities.APIMovieCast
import co.com.mova.data.repositories.IMovieRepository
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by oscarg798 on 4/23/18.
 */
class GetMovieCastUseCase(mSubscribeOnScheduler: Scheduler,
                          mObserverOnScheduler: Scheduler) :
        SingleUseCase<List<MovieCast>, Int>(mSubscribeOnScheduler, mObserverOnScheduler) {

    @Inject
    lateinit var mMovieRepository: IMovieRepository

    override fun buildUseCase(params: Int): Single<List<MovieCast>> {
        return Single.fromObservable(mMovieRepository.getMovieCastFromAPI(params))
                .doOnError {
                    mMovieRepository.getMovieCastFromDB()
                            .map {
                                APIMovieCast(it.id, it.name, it.profilePath, it.character)
                            }
                }
                .map {
                    it.map {
                        val dbCast = mMovieRepository.getMovieCast(it.id)
                        if (dbCast !== null) {
                            val moviesIds = ArrayList<Int>()
                            moviesIds.addAll(dbCast.movieIds)
                            if (!moviesIds.contains(params)) moviesIds.add(params)
                            mMovieRepository.updateMovieCast(DBMovieCast(dbCast.id, dbCast.name, dbCast.profilePath, dbCast.character, moviesIds))
                        } else {
                            mMovieRepository.insertMovieCast(DBMovieCast(it.id, it.name, it.profilePath, it.character, arrayListOf(params)))
                        }

                        MovieCast(it.id, it.name, it.profilePath, it.character)
                    }
                }
    }
}