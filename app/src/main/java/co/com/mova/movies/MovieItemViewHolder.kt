package co.com.mova.movies

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import co.com.mova.R
import com.arbelkilani.bicoloredprogress.BiColoredProgress

/**
 * Created by oscarg798 on 4/7/18.
 */
class MovieItemViewHolder(mItemView: View) : RecyclerView.ViewHolder(mItemView) {

    val mIVMoviePoster = mItemView.findViewById<ImageView>(R.id.mIVMoviePoster)
    val mTVMovieTitle = mItemView.findViewById<TextView>(R.id.mTVMovieTitle)
    val mBCPMovieVotes = mItemView.findViewById<BiColoredProgress>(R.id.mBCPMovieVotes)
    val mTVGenres = mItemView.findViewById<TextView>(R.id.mTVGenres)
    val mTVMovieReleaseDate = mItemView.findViewById<TextView>(R.id.mTVMovieReleaseDate)
}