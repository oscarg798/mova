package co.com.mova.core.use_cases.movie

import co.com.mova.core.entities.Movie
import co.com.mova.core.use_cases.base.SingleUseCase
import co.com.mova.data.repositories.IMovieRepository
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by oscarg798 on 4/7/18.
 */
class GetFavoriteMoviesUseCase(mSubscribeOnScheduler: Scheduler,
                               mObserverOnScheduler: Scheduler)
    : SingleUseCase<List<Movie>, Any?>(mSubscribeOnScheduler, mObserverOnScheduler) {

    @Inject
    lateinit var mMovieRepository: IMovieRepository

    override fun buildUseCase(params: Any?): Single<List<Movie>> {
        return Single.create {
            val dbFavoriteMovies = mMovieRepository.getFavoriteMovies()
            it.onSuccess(dbFavoriteMovies.map { dbMovie ->
                Movie(dbMovie.id, dbMovie.voteCount, dbMovie.voteAverage, dbMovie.title,
                        dbMovie.popularity, dbMovie.posterPath, ArrayList(), dbMovie.overview,
                        dbMovie.releaseDate, dbMovie.favorite)
            })

        }
    }
}