package co.com.mova.movies

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v4.view.MenuItemCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
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
        setSupportActionBar(mToolbar)
        mRVMovies?.setHasFixedSize(false)
        mRVMovies?.layoutManager = LinearLayoutManager(this)
        mRVMovies?.adapter = MoviesAdapter(mMoviesCallback = mPresenter)
        mRVMovies?.addOnScrollListener(EndLessScrollListener(mPresenter))
        mSRLMovies?.setOnRefreshListener(mPresenter)
        mSRLMovies?.isEnabled = false
        mFABFilter?.setOnClickListener {
            mPresenter.toggleFavorites()
        }


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

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.let {


        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menu?.let {
            menuInflater.inflate(R.menu.movies_menu, menu)
            menu.findItem(R.id.action_search)?.let {
                val searchView = MenuItemCompat.getActionView(it) as SearchView
                val edit = searchView.findViewById<EditText>(android.support.v7.appcompat.R.id.search_src_text)
                edit.setTextColor(Color.WHITE)
                edit.setHintTextColor(Color.WHITE)
                searchView.setOnQueryTextListener(mPresenter)
                MenuItemCompat.setOnActionExpandListener(it, mPresenter)
            }
        }
        return super.onCreateOptionsMenu(menu)
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

    override fun getMoviesInAdapter(): ArrayList<Movie>? {

        mRVMovies?.adapter?.let {
            return ArrayList((it as MoviesAdapter).getMovies())
        }
        return null
    }
}
