package co.com.mova.movies

import co.com.mova.core.entities.Movie

/**
 * Created by oscarg798 on 4/7/18.
 */
interface IMoviesCallback{

    fun onClick(movie: Movie)
}