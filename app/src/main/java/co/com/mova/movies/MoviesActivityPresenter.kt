package co.com.mova.movies

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import android.os.Bundle
import android.view.MenuItem
import co.com.mova.core.entities.Movie
import co.com.mova.core.use_cases.base.ISingleUseCase
import co.com.mova.data.MOVIE_ID
import co.com.mova.detail.MovieDetailActivity
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by oscarg798 on 4/7/18
 */
class MoviesActivityPresenter : IMoviesActivityPresenter {

    override var mView: IMoviesActivityView? = null

    private var mActualPage = 1

    private var mIsLoading = false

    private var mHasMoreResults = false

    private var mDisposableBag = CompositeDisposable()

    private var mIsShowingFavorites = false

    @Inject
    lateinit var mGetMoviesUseCase: ISingleUseCase<Pair<Boolean, List<Movie>>, Int>

    @Inject
    lateinit var mGetFavoriteMoviesUseCase: ISingleUseCase<List<Movie>, Any?>

    private var mMoviesBeforeSearch: ArrayList<Movie>? = null

    private var mIsSearching = false


    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        if (!mIsShowingFavorites) {
            mActualPage = 1
            mView?.clear()
            getMovies()
        } else {
            getFavoritesMovies()
        }


    }

    private fun getMovies() {
        if (!mIsLoading) {
            mIsLoading = true
            mView?.showProgressBar()
            val disposable = object : DisposableSingleObserver<Pair<Boolean, List<Movie>>>() {
                override fun onSuccess(t: Pair<Boolean, List<Movie>>) {
                    mHasMoreResults = t.first
                    mView?.showMovies(t.second)
                    mView?.hideProgressBar()
                    mDisposableBag.remove(this)
                    mIsLoading = false

                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    mView?.hideProgressBar()
                    mDisposableBag.remove(this)
                    mIsLoading = false
                }
            }
            mDisposableBag.add(disposable)
            mGetMoviesUseCase.execute(mActualPage, disposable)
        }


    }

    override fun loadMore() {
        if (mHasMoreResults && !mIsLoading && !mIsShowingFavorites) {
            mActualPage++
            getMovies()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private fun onStop() {
        mDisposableBag.clear()
    }

    override fun onRefresh() {
        if (!mIsLoading && !mIsShowingFavorites && !mIsSearching) {
            mActualPage = 1
            mView?.clear()
            getMovies()

        } else {
            mView?.hideProgressBar()
        }
    }

    override fun onClick(movie: Movie) {
        val bundle = Bundle()
        bundle.putInt(MOVIE_ID, movie.id)
        mView?.navigate(MovieDetailActivity::class.java, bundle)

    }

    override fun toggleFavorites() {
        if (!mIsLoading && !mIsSearching) {
            if (mIsShowingFavorites) {
                mIsShowingFavorites = false
                onRefresh()
            } else {
                getFavoritesMovies()
            }

        }
    }

    private fun getFavoritesMovies() {
        mIsShowingFavorites = true
        mIsLoading = true
        mView?.showProgressBar()
        mView?.clear()
        val disposable = object : DisposableSingleObserver<List<Movie>>() {
            override fun onSuccess(t: List<Movie>) {
                mView?.showMovies(t)
                mView?.hideProgressBar()
                mDisposableBag.remove(this)
                mIsLoading = false
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
                mView?.hideProgressBar()
                mDisposableBag.remove(this)
                mIsLoading = false
            }
        }
        mDisposableBag.add(disposable)
        mGetFavoriteMoviesUseCase.execute(null, disposable)
    }

    override fun isShowingFavorites(): Boolean = mIsShowingFavorites

    override fun onQueryTextSubmit(query: String?): Boolean {
        onQueryChange(query, true)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        onQueryChange(newText, false)
        return true
    }

    override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
        mIsSearching = true
        return true
    }

    override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
        mIsSearching = false
        mMoviesBeforeSearch?.let {
            mView?.showMovies(it)
        }

        mMoviesBeforeSearch = null
        return true
    }

    private fun onQueryChange(query: String?, querySubmit: Boolean) {
        if (mMoviesBeforeSearch !== null) {
            mView?.clear()
            mView?.showMovies(mMoviesBeforeSearch!!)
        }

        mMoviesBeforeSearch = mView?.getMoviesInAdapter()
        mView?.clear()

        query?.let {
            mView?.showProgressBar()

            val disposable = object : DisposableSingleObserver<List<Movie>>() {
                override fun onSuccess(t: List<Movie>) {
                    mView?.clear()
                    mView?.showMovies(t)
                    mView?.hideProgressBar()
                    mDisposableBag.remove(this)
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    mView?.hideProgressBar()
                    mDisposableBag.remove(this)
                }
            }

            mDisposableBag.add(disposable)

            Single.create<List<Movie>> { emitter ->
                val result = ArrayList<Movie>()
                mMoviesBeforeSearch?.filter {
                    if (!querySubmit)
                        it.title.toLowerCase().contains(query.toLowerCase())
                    else
                        it.title.toLowerCase() == query.toLowerCase()
                }?.forEach {
                    result.add(it)
                }


                emitter.onSuccess(result)

            }.delay(100, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())

                    .subscribe(disposable)


        }
    }


}