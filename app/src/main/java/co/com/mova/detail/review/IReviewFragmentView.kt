package co.com.mova.detail.review

import android.os.Bundle
import co.com.mova.core.entities.MovieReview
import co.com.post.IBaseView

/**
 * Created by oscarg798 on 4/23/18.
 */
interface IReviewFragmentView:IBaseView{

    fun getArguments():Bundle?

    fun showReviews(reviews:List<MovieReview>)

    fun clear()
}