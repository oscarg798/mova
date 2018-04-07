package co.com.mova.core.use_cases.movie

import co.com.mova.core.entities.Genre
import co.com.mova.core.entities.Movie
import co.com.mova.core.use_cases.UseCaseUtils
import co.com.mova.core.use_cases.base.SingleUseCase
import co.com.mova.core.use_cases.genre.GetMovieGenresUseCase
import co.com.mova.data.local.entities.DBMovie
import co.com.mova.data.network.entities.APIMovie
import co.com.mova.data.repositories.IGenreRepository
import co.com.mova.data.repositories.IMovieRepository
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by oscarg798 on 4/7/18.
 */
class GetMoviesUseCase(mSubscribeOnScheduler: Scheduler,
                       mObserverOnScheduler: Scheduler) :
        SingleUseCase<Pair<Boolean, List<Movie>>, Int>(mSubscribeOnScheduler, mObserverOnScheduler) {

    @Inject
    lateinit var mMovieRepository: IMovieRepository

    @Inject
    lateinit var mGenreRepository: IGenreRepository

    override fun buildUseCase(params: Int): Single<Pair<Boolean, List<Movie>>> {
        return Single.fromObservable(mMovieRepository.getMoviesFromAPI(params))
                .map {
                    Pair(it.first, it.second.map {
                        var favorite = false
                        val dbMovie = mMovieRepository.getMovie(it.id)
                        if (dbMovie !== null) {
                            favorite = dbMovie.favorite
                            mMovieRepository.update(DBMovie(it.id, it.voteCount, it.voteAverage,
                                    it.title, it.popularity, it.posterPath,
                                    it.genreIds, it.overview, it.releaseDate, favorite))
                        } else {
                            mMovieRepository.insertMovie(DBMovie(it.id, it.voteCount,
                                    it.voteAverage, it.title, it.popularity, it.posterPath,
                                    it.genreIds, it.overview, it.releaseDate, favorite))
                        }
                        Movie(it.id, it.voteCount, it.voteAverage, it.title, it.popularity, it.posterPath,
                                UseCaseUtils.instance.getMovieGenres(it.genreIds, mGenreRepository), it.overview, it.releaseDate, favorite)
                    }.sortedByDescending { it.popularity })
                }
    }


}