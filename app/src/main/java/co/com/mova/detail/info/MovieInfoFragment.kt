package co.com.mova.detail.info


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import co.com.mova.R
import co.com.mova.core.entities.Movie
import co.com.mova.data.MOVIE
import kotlinx.android.synthetic.main.fragment_movie_info.*
import android.text.method.ScrollingMovementMethod




/**
 * A simple [Fragment] subclass.
 */
class MovieInfoFragment : Fragment(),IMovieInfoFragmentView {

    private val mPresenter: IMovieInfoFragmentPresenter = MovieInfoFragmentPresenter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(mPresenter)
        mPresenter.bind(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponents()
    }

    override fun initComponents() {
        mTVMovieOverview?.movementMethod = ScrollingMovementMethod()
    }

    override fun showProgressBar() {
    }

    override fun hideProgressBar() {
    }

    override fun showMovieOverview(overview: String) {
        mTVMovieOverview?.text = overview
    }

    override fun showMovieGenres(genres: String) {
        mTVGenres?.text = genres
    }


    override fun navigate(destination: Class<*>, arguments: Bundle?, options:Pair<View,String>?) {
    }



    companion object {

        fun newInstance(movie: Movie): MovieInfoFragment {
            val fragment = MovieInfoFragment()
            val arguments = Bundle()
            arguments.putParcelable(MOVIE, movie)
            fragment.arguments = arguments
            return fragment

        }
    }

}// Required empty public constructor
