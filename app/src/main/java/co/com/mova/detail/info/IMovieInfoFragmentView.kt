package co.com.mova.detail.info

import android.os.Bundle
import co.com.post.IBaseView

/**
 * Created by oscarg798 on 4/23/18.
 */
interface IMovieInfoFragmentView : IBaseView {

    fun showMovieOverview(overview: String)

    fun showMovieGenres(genres: String)

    fun getArguments(): Bundle?


}