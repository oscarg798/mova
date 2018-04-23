package co.com.mova.detail.info

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import co.com.mova.core.entities.Movie
import co.com.mova.data.MOVIE

/**
 * Created by oscarg798 on 4/23/18.
 */
class MovieInfoFragmentPresenter : IMovieInfoFragmentPresenter {

    override var mView: IMovieInfoFragmentView? = null


    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun setUp() {
        val movie = mView?.getArguments()?.getParcelable<Movie>(MOVIE)
        movie?.let {
            mView?.showMovieGenres(getMovieGenres(it))
            mView?.showMovieOverview(it.overview)
        }
    }

    private fun getMovieGenres(movie: Movie): String {
        var genresString = ""
        val genres = movie.genres.take(3)
        genres.forEach {
            genresString += it.name
            if (movie.genres.indexOf(it) < 1 && genres.size > 1) {
                genresString += "| "
            }
        }
        return genresString
    }
}