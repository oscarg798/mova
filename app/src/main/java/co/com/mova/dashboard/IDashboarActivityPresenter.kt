package co.com.mova.dashboard

import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.SearchView
import co.com.mova.ISearchableMovieFragment
import co.com.post.IBasePresenter

/**
 * Created by oscarg798 on 4/27/18.
 */
interface IDashboarActivityPresenter : IBasePresenter<IDashboardActivityView>, SearchView.OnQueryTextListener,
        MenuItemCompat.OnActionExpandListener {

    fun onFragmentChanged(fragment: ISearchableMovieFragment?)

}