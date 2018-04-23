package co.com.mova.data.repositories

import co.com.mova.data.local.dao.MovieCastDAO
import co.com.mova.data.local.dao.MovieDAO
import co.com.mova.data.local.dao.MovieReviewDAO
import co.com.mova.data.local.entities.DBMovie
import co.com.mova.data.local.entities.DBMovieCast
import co.com.mova.data.local.entities.DBMovieReview
import co.com.mova.data.network.entities.APIMovie
import co.com.mova.data.network.entities.APIMovieCast
import co.com.mova.data.network.entities.APIMovieReview
import co.com.mova.data.network.entities.ApiMovieVideo
import co.com.mova.data.network.routes.IMovieRoute
import io.reactivex.Observable
import javax.inject.Inject


/**
 * Created by oscarg798 on 4/7/18.
 */
class MovieRepository : IMovieRepository {

    @Inject
    lateinit var mMovieRoute: IMovieRoute

    @Inject
    lateinit var mMovieDAO: MovieDAO

    @Inject
    lateinit var mMovieReviewDAO:MovieReviewDAO

    @Inject
    lateinit var mMovieCastDAO: MovieCastDAO

    override fun getMoviesFromAPI(page: Int): Observable<Pair<Boolean, List<APIMovie>>> {
        return mMovieRoute.getPopularMovies(page).map {
            Pair(it.totalPages > page, it.results)
        }
    }

    override fun getMovie(id: Int): DBMovie? {
        return mMovieDAO.get(id)
    }

    override fun getFavoriteMovies(): List<DBMovie> {
        return mMovieDAO.getFavorites()
    }

    override fun makeMovieFavorite(id: Int) {
        mMovieDAO.makeMovieFavorite(id)
    }

    override fun insertMovie(dbMovie: DBMovie) {
        mMovieDAO.insert(dbMovie)
    }

    override fun update(dbMovie: DBMovie) {
        mMovieDAO.update(dbMovie)
    }

    override fun getMovieVideos(id: Int): Observable<List<ApiMovieVideo>> {
        return mMovieRoute.getMovieVideos(id).map {
            it.results
        }
    }

    override fun removeMovieFromFavorites(id: Int) {
        mMovieDAO.removeMovieFromFavorite(id)
    }

    override fun getMovieCastFromAPI(movieId: Int): Observable<List<APIMovieCast>> {
         return mMovieRoute.getMovieCredits(movieId).map {
             it.cast
         }
    }

    override fun getMovieCastFromDB(): List<DBMovieCast> {
        return mMovieCastDAO.getAll()
    }

    override fun insertMovieCast(dbMovieCast: DBMovieCast) {
        mMovieCastDAO.insert(dbMovieCast)
    }

    override fun updateMovieCast(dbMovieCast: DBMovieCast) {
        mMovieCastDAO.update(dbMovieCast)
    }

    override fun getMovieReviewsFromAPI(movieId: Int): Observable<List<APIMovieReview>> {
        return mMovieRoute.getMovieReview(movieId).map {
            it.results
        }
    }

    override fun getMovieReviewsFromDB(movieId: Int): List<DBMovieReview> {
        return mMovieReviewDAO.getByMovie(movieId)
    }

    override fun insertMovieReview(dbMovieReview: DBMovieReview) {
        mMovieReviewDAO.insert(dbMovieReview)
    }

    override fun getMovieCast(id: Int): DBMovieCast? {
        return mMovieCastDAO.get(id)
    }
}