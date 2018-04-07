package co.com.mova.movies

import android.support.v4.widget.SwipeRefreshLayout
import co.com.post.IBasePresenter

/**
 * Created by oscarg798 on 4/7/18.
 */
interface IMoviesActivityPresenter : IBasePresenter<IMoviesActivityView>, IMoviesScrollListener,
        SwipeRefreshLayout.OnRefreshListener, IMoviesCallback