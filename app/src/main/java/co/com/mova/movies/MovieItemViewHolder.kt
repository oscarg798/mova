package co.com.mova.movies

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import co.com.mova.R

/**
 * Created by oscarg798 on 4/7/18.
 */
class MovieItemViewHolder(mItemView:View):RecyclerView.ViewHolder(mItemView){

    val mIVMoviePoster = mItemView.findViewById<ImageView>(R.id.mIVMoviePoster)
    val mTVMovieTitle = mItemView.findViewById<TextView>(R.id.mTVMovieTitle)
    val mTVVoteAverage = mItemView.findViewById<TextView>(R.id.mTVVoteAverage)
}