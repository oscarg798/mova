package co.com.mova.movies

import android.os.Bundle
import co.com.mova.core.entities.Movie
import co.com.post.IBaseView

/**
 * Created by oscarg798 on 4/7/18.
 */
interface IMoviesFragmentView : IBaseView {

    fun showMovies(movies: List<Movie>)

    fun clear()

    fun getMoviesInAdapter(): ArrayList<Movie>?

    fun getArguments(): Bundle?


}