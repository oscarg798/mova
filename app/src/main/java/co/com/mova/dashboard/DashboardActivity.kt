package co.com.mova.dashboard

import android.app.Fragment
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.MenuItemCompat
import android.support.v4.view.ViewPager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.View
import android.widget.EditText
import co.com.mova.ISearchableMovieFragment
import co.com.mova.R
import co.com.mova.ViewPagerAdapter
import co.com.mova.movies.MoviesFragment
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity(), IDashboardActivityView {

    private val mPresenter: IDashboarActivityPresenter = DashboardActivityPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        lifecycle.addObserver(mPresenter)
        mPresenter.bind(this)
        initComponents()
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

    override fun initComponents() {
        mVPMovieCategories?.adapter = ViewPagerAdapter(supportFragmentManager,
                arrayListOf(MoviesFragment.newInstance(1),
                        MoviesFragment.newInstance(2),
                        MoviesFragment.newInstance(3)),
                arrayListOf(getString(R.string.popular_label),
                        getString(R.string.top_rated_label),
                        getString(R.string.upcoming_label)))

        mVPMovieCategories?.offscreenPageLimit = 0

        mVPMovieCategories?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                mPresenter.onFragmentChanged((mVPMovieCategories?.adapter as? ViewPagerAdapter?)?.getFragmentAtPosition(position) as? ISearchableMovieFragment)
            }
        })


        mPresenter.onFragmentChanged((mVPMovieCategories?.adapter as? ViewPagerAdapter?)?.getFragmentAtPosition(0) as? ISearchableMovieFragment)

        mTLMovieCategories?.setupWithViewPager(mVPMovieCategories)
    }

    override fun showProgressBar() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun hideProgressBar() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun navigate(destination: Class<*>, arguments: Bundle?, options: Pair<View, String>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
