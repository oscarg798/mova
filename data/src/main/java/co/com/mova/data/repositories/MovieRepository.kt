package co.com.mova.data.repositories

import co.com.mova.data.local.dao.MovieDAO
import co.com.mova.data.local.entities.DBMovie
import co.com.mova.data.network.entities.APIMovie
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
            it.response
        }
    }

    override fun removeMovieFromFavorites(id: Int) {
        mMovieDAO.removeMovieFromFavorite(id)
    }


}