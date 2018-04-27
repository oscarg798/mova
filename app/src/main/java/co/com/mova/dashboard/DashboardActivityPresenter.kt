package co.com.mova.dashboard

import android.view.MenuItem
import co.com.mova.ISearchableMovieFragment

/**
 * Created by oscarg798 on 4/27/18.
 */
class DashboardActivityPresenter : IDashboarActivityPresenter {

    override var mView: IDashboardActivityView? = null

    private var mCurrentFragmentSearchable: ISearchableMovieFragment? = null


    override fun onFragmentChanged(fragment: ISearchableMovieFragment?) {
        mCurrentFragmentSearchable = fragment
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        mCurrentFragmentSearchable?.onQueryTextSubmit(query)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        mCurrentFragmentSearchable?.onQueryTextChange(newText)
        return true
    }

    override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
        mCurrentFragmentSearchable?.onMenuItemActionExpand(item)
        return true
    }

    override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
        mCurrentFragmentSearchable?.onMenuItemActionCollapse(item)
        return true
    }
}