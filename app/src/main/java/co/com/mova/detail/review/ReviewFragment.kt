package co.com.mova.detail.review


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.com.mova.BaseApplication

import co.com.mova.R
import co.com.mova.core.entities.MovieReview
import co.com.mova.data.MOVIE_ID
import kotlinx.android.synthetic.main.fragment_blank.*


/**
 * A simple [Fragment] subclass.
 */
class ReviewFragment : Fragment(), IReviewFragmentView {


    private lateinit var mPresenter: IReviewFragmentPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val presenter = ReviewFragmentPresenter()
        (activity?.application as? BaseApplication)?.appComponent?.inject(presenter)
        mPresenter = presenter
        lifecycle.addObserver(presenter)
        mPresenter.bind(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponents()
    }


    override fun initComponents() {
        mRVReviews?.layoutManager = LinearLayoutManager(activity)
        mRVReviews?.adapter = ReviewAdapter()
        mRVReviews?.setHasFixedSize(false)

    }

    override fun showProgressBar() {
        mRVReviews?.visibility = View.GONE
        mPBReview?.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        mRVReviews?.visibility = View.VISIBLE
        mPBReview?.visibility = View.GONE
    }

    override fun navigate(destination: Class<*>, arguments: Bundle?, options:Pair<View,String>?) {

    }



    override fun showReviews(reviews: List<MovieReview>) {
        (mRVReviews?.adapter as? ReviewAdapter)?.add(reviews)
    }

    override fun clear() {
        (mRVReviews?.adapter as? ReviewAdapter)?.clear()
    }

    companion object {

        fun newInstance(movieID: Int): ReviewFragment {
            val fragment = ReviewFragment()
            val bundle = Bundle()
            bundle.putInt(MOVIE_ID, movieID)
            fragment.arguments = bundle
            return fragment

        }
    }

}// Required empty public constructor
