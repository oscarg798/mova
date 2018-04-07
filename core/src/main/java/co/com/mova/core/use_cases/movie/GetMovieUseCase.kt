package co.com.mova.core.use_cases.movie

import co.com.mova.core.entities.Movie
import co.com.mova.core.use_cases.base.SingleUseCase
import co.com.mova.core.use_cases.genre.GetMovieGenresUseCase
import co.com.mova.data.repositories.IMovieRepository
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by oscarg798 on 4/7/18.
 */
class GetMovieUseCase(mSubscribeOnScheduler: Scheduler,
                      mObserverOnScheduler: Scheduler) :
        SingleUseCase<Movie, Int>(mSubscribeOnScheduler, mObserverOnScheduler) {


    @Inject
    lateinit var mMovieRepository: IMovieRepository

    override fun buildUseCase(params: Int): Single<Movie> {
        return Single.create<Pair<Movie, List<Int>>> {
            val dbMovie = mMovieRepository.getMovie(params)
            it.onSuccess(Pair(Movie(dbMovie!!.id, dbMovie.voteCount, dbMovie.voteAverage, dbMovie.title,
                    dbMovie.popularity, dbMovie.posterPath, ArrayList(), dbMovie.overview,
                    dbMovie.releaseDate, dbMovie.favorite), dbMovie.genreIds))

        }.flatMap { pair ->
            GetMovieGenresUseCase(Schedulers.io(), Schedulers.io())
                    .buildUseCase(pair.second)
                    .map {
                        pair.first.genres.addAll(it)
                        pair.first
                    }
        }
    }
}