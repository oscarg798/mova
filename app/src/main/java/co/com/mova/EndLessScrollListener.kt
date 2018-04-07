package co.com.mova

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import co.com.mova.movies.IMoviesScrollListener

/**
 * Created by oscarg798 on 4/7/18.
 */
class EndLessScrollListener(private val mScrollListener: IMoviesScrollListener) : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (dy > 0) {
            recyclerView?.let {
                val visibleItemCount = recyclerView.childCount
                val totalCount = (recyclerView.layoutManager as LinearLayoutManager).itemCount
                val firstVisibleItem = (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

                if ((firstVisibleItem + visibleItemCount) >= totalCount) {
                    mScrollListener.loadMore()
                }

            }
        }


    }

}