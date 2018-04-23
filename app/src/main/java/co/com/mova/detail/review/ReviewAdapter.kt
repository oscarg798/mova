package co.com.mova.detail.review

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import co.com.mova.R
import co.com.mova.core.entities.MovieReview

/**
 * Created by oscarg798 on 4/23/18.
 */
class ReviewAdapter(private val mReviews: ArrayList<MovieReview> = ArrayList()) :
        RecyclerView.Adapter<ReviewItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewItemViewHolder {
        return ReviewItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.review_item, parent, false))
    }

    override fun getItemCount(): Int {
        return mReviews.size
    }

    override fun onBindViewHolder(holder: ReviewItemViewHolder, position: Int) {
        holder.mTVReviewAuthor?.text = "${mReviews[position].author}:"
        holder.mTVReviewContent?.text = mReviews[position].content
    }

    fun add(reviews: List<MovieReview>) {
        mReviews.addAll(reviews)
        notifyDataSetChanged()
    }

    fun clear() {
        mReviews.clear()
        notifyDataSetChanged()
    }


}