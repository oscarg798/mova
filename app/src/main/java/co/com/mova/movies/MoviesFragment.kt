package co.com.mova.movies

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import co.com.mova.BaseApplication
import co.com.mova.EndLessScrollListener
import co.com.mova.ISearchableMovieFragment
import co.com.mova.R
import co.com.mova.core.entities.Movie
import co.com.mova.data.CATEGORY_TYPE
import kotlinx.android.synthetic.main.activity_movies.*


class MoviesFragment : Fragment(), IMoviesFragmentView, ISearchableMovieFragment{

    lateinit var mPresenter: IMoviesFragmentPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val presenter = MoviesFragmentPresenter()
        (activity?.application as? BaseApplication)?.appComponent?.inject(presenter)
        mPresenter = presenter
        lifecycle.addObserver(presenter)
        mPresenter.bind(this)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponents()
    }

    override fun initComponents() {
        mRVMovies?.setHasFixedSize(false)
        mRVMovies?.layoutManager = LinearLayoutManager(activity)
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

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.let {


        }
        return super.onOptionsItemSelected(item)
    }




    override fun navigate(destination: Class<*>, arguments: Bundle?, options: Pair<View, String>?) {

        activity?.let {
            val intent = Intent(activity, destination)
            arguments?.let {
                intent.putExtras(arguments)
            }

            if (options !== null) {

                startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(it,
                        android.support.v4.util.Pair(options.first, options.second)).toBundle())
            } else {
                startActivity(intent)

            }
        }


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

    override fun onQueryTextSubmit(query: String?): Boolean {
        mPresenter.onQueryTextSubmit(query)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        mPresenter.onQueryTextChange(newText)
        return true
    }

    override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
        mPresenter.onMenuItemActionExpand(item)
        return true
    }

    override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
        mPresenter.onMenuItemActionCollapse(item)
        return true
    }

    companion object {

        fun newInstance(category:Int):MoviesFragment{
            val fragment = MoviesFragment()
            val argument = Bundle()
            argument.putInt(CATEGORY_TYPE,category)
            fragment.arguments = argument
            return fragment
        }
    }
}
