package co.com.mova.core.use_cases.movie

import co.com.mova.core.entities.Genre
import co.com.mova.core.entities.Movie
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
                    dbMovie.popularity, dbMovie.posterPath, getMovieGenres(dbMovie.genreIds), dbMovie.overview,
                    dbMovie.releaseDate, dbMovie.favorite))
        }
    }

    private fun getMovieGenres(params: List<Int>):ArrayList<Genre> {
        val genres = ArrayList<Genre>()
        params.forEach {
            mGenreRepository.getGenre(it)?.let {
                genres.add(Genre(it.id, it.name))
            }

        }

        return genres
    }
}