package co.com.mova.data.repositories

import co.com.mova.data.local.entities.DBMovie
import co.com.mova.data.local.entities.DBMovieCast
import co.com.mova.data.local.entities.DBMovieReview
import co.com.mova.data.network.entities.APIMovie
import co.com.mova.data.network.entities.APIMovieCast
import co.com.mova.data.network.entities.APIMovieReview
import co.com.mova.data.network.entities.ApiMovieVideo
import io.reactivex.Observable

/**
 * Created by oscarg798 on 4/7/18.
 */
interface IMovieRepository {

    fun getMoviesFromAPI(page: Int, type: Int): Observable<Pair<Boolean, List<APIMovie>>>

    fun getMovie(id: Int): DBMovie?

    fun getFavoriteMovies(): List<DBMovie>

    fun makeMovieFavorite(id: Int)

    fun removeMovieFromFavorites(id: Int)

    fun insertMovie(dbMovie: DBMovie)

    fun update(dbMovie: DBMovie)

    fun getMovieVideos(id: Int): Observable<List<ApiMovieVideo>>

    fun getMovieCast(id: Int):DBMovieCast?

    fun getMovieCastFromAPI(movieId: Int): Observable<List<APIMovieCast>>

    fun getMovieCastFromDB(): List<DBMovieCast>

    fun insertMovieCast(dbMovieCast: DBMovieCast)

    fun updateMovieCast(dbMovieCast: DBMovieCast)

    fun getMovieReviewsFromAPI(movieId: Int): Observable<List<APIMovieReview>>

    fun getMovieReviewsFromDB(movieId: Int): List<DBMovieReview>

    fun insertMovieReview(dbMovieReview: DBMovieReview)
}