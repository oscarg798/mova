package co.com.mova.data.repositories

import co.com.mova.data.local.entities.DBMovie
import co.com.mova.data.network.entities.APIMovie
import io.reactivex.Observable

/**
 * Created by oscarg798 on 4/7/18.
 */
interface IMovieRepository {

    fun getMoviesFromAPI(page: Int): Observable<Pair<Boolean, List<APIMovie>>>

    fun getMovie(id: Int): DBMovie?

    fun getFavoriteMovies(): List<DBMovie>

    fun makeMovieFavorite(id: Int)

    fun insertMovie(dbMovie: DBMovie)

    fun update(dbMovie: DBMovie)
}