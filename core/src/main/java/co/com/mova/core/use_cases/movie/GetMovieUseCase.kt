package co.com.mova.core.use_cases.movie

import co.com.mova.core.entities.Genre
import co.com.mova.core.entities.Movie
import co.com.mova.core.use_cases.UseCaseUtils
import co.com.mova.core.use_cases.base.SingleUseCase
import co.com.mova.core.use_cases.genre.GetMovieGenresUseCase
import co.com.mova.data.repositories.IGenreRepository
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

    @Inject
    lateinit var mGenreRepository: IGenreRepository


    override fun buildUseCase(params: Int): Single<Movie> {
        return Single.create<Movie> {
            val dbMovie = mMovieRepository.getMovie(params)
            it.onSuccess(Movie(dbMovie!!.id, dbMovie.voteCount, dbMovie.voteAverage, dbMovie.title,
                    dbMovie.popularity, dbMovie.posterPath,
                    UseCaseUtils.instance.getMovieGenres(dbMovie.genreIds, mGenreRepository), dbMovie.overview,
                    dbMovie.releaseDate, dbMovie.favorite))
        }
    }

}