package co.com.mova.detail.review

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import co.com.mova.R

/**
 * Created by oscarg798 on 4/23/18.
 */
class ReviewItemViewHolder(mItemView:View):RecyclerView.ViewHolder(mItemView){

    val mTVReviewContent = mItemView.findViewById<TextView>(R.id.mTVReviewContent)
    val mTVReviewAuthor = mItemView.findViewById<TextView>(R.id.mTVReviewAuthor)

}