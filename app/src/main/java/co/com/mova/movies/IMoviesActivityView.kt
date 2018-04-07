package co.com.mova.movies

import co.com.mova.core.entities.Movie
import co.com.post.IBaseView

/**
 * Created by oscarg798 on 4/7/18.
 */
interface IMoviesActivityView : IBaseView {

    fun showMovies(movies: List<Movie>)

}