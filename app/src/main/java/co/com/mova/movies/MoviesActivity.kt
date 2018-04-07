package co.com.mova.movies

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import co.com.mova.BaseApplication
import co.com.mova.EndLessScrollListener
import co.com.mova.R
import co.com.mova.core.entities.Movie
import kotlinx.android.synthetic.main.activity_movies.*

class MoviesActivity : AppCompatActivity(), IMoviesActivityView {

    lateinit var mPresenter: IMoviesActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)
        initComponents()
    }

    override fun initComponents() {
        val presenter = MoviesActivityPresenter()
        (application as BaseApplication).appComponent.inject(presenter)
        mPresenter = presenter
        lifecycle.addObserver(presenter)
        mPresenter.bind(this)
        mRVMovies?.setHasFixedSize(false)
        mRVMovies?.layoutManager = LinearLayoutManager(this)
        mRVMovies?.adapter = MoviesAdapter(mMoviesCallback = mPresenter)
        mRVMovies?.addOnScrollListener(EndLessScrollListener(mPresenter))
        mSRLMovies?.setOnRefreshListener(mPresenter)
    }

    override fun showProgressBar() {
        mSRLMovies?.isRefreshing = true
    }

    override fun hideProgressBar() {
        mSRLMovies?.isRefreshing = false
    }

    override fun showMovies(movies: List<Movie>) {
        mRVMovies?.adapter?.let {
            (it as MoviesAdapter).addMovies(movies)
        }
    }

    override fun navigate(destination: Class<*>, arguments: Bundle?) {
        val intent = Intent(this, destination)
        arguments?.let {
            intent.putExtras(arguments)
        }
        startActivity(intent)
    }

    override fun clear() {
        mRVMovies?.adapter?.let {
            (it as MoviesAdapter).clear()
        }
    }
}
