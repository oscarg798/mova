package co.com.mova

import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.SearchView

/**
 * Created by oscarg798 on 4/27/18.
 */
interface ISearchableMovieFragment: SearchView.OnQueryTextListener,
        MenuItemCompat.OnActionExpandListener{

}