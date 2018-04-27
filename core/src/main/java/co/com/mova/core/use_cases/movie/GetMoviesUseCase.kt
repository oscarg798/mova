package co.com.mova.core.use_cases.movie

import co.com.mova.core.entities.Movie
import co.com.mova.core.use_cases.UseCaseUtils
import co.com.mova.core.use_cases.base.SingleUseCase
import co.com.mova.data.local.entities.DBMovie
import co.com.mova.data.network.entities.APIMovie
import co.com.mova.data.network.responses.GetMoviesResponse
import co.com.mova.data.repositories.IGenreRepository
import co.com.mova.data.repositories.IMovieRepository
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by oscarg798 on 4/7/18.
 */
class GetMoviesUseCase(mSubscribeOnScheduler: Scheduler,
                       mObserverOnScheduler: Scheduler) :
        SingleUseCase<Pair<Boolean, List<Movie>>, Pair<Int, Int>>(mSubscribeOnScheduler, mObserverOnScheduler) {

    @Inject
    lateinit var mMovieRepository: IMovieRepository

    @Inject
    lateinit var mGenreRepository: IGenreRepository

    override fun buildUseCase(params: Pair<Int, Int>): Single<Pair<Boolean, List<Movie>>> {
        return Single.fromObservable(mMovieRepository.getMoviesFromAPI(params.first, params.second))
                .onErrorReturn {
                    val movies = mMovieRepository.getMovies().map {
                        APIMovie(it.id, it.voteCount, it.voteAverage,
                                it.title, it.popularity, it.posterPath, it.genreIds, it.overview, it.releaseDate)
                    }

                    Pair(false, when (params.second) {
                        1 -> movies.sortedByDescending { it.popularity }
                        2 -> movies.sortedByDescending { it.voteAverage }
                        else -> movies.sortedByDescending { it.releaseDate }
                    })
                }
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
                    })
                }
    }


}