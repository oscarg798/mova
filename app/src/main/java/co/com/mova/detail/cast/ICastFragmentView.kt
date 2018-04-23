package co.com.mova.detail.cast

import android.os.Bundle
import co.com.mova.core.entities.MovieCast
import co.com.post.IBaseView

/**
 * Created by oscarg798 on 4/23/18.
 */
interface ICastFragmentView : IBaseView {

    fun showCast(cast: List<MovieCast>)

    fun clear()

    fun getArguments(): Bundle?
}