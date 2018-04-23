package co.com.mova.detail

import co.com.mova.core.entities.Movie
import co.com.post.IBaseView

/**
 * Created by oscarg798 on 4/7/18.
 */
interface IMovieDetailActivityView : IBaseView {

    fun loadMoviePoster(poster:String)

    fun showMovieTitle(title: String)

    fun showMovieReleaseDate(date: String)

    fun showMovieRaiting(votes: Float)

    fun changeFavoriteIcon(isFavorite: Boolean)

    fun getMovieId(): Int

    fun showMovieTrailer(key: String)

    fun setUpViewPager(movie:Movie)


}