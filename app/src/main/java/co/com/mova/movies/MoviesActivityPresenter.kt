package co.com.mova.movies

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import android.os.Bundle
import co.com.mova.core.entities.Movie
import co.com.mova.core.use_cases.base.ISingleUseCase
import co.com.mova.data.MOVIE_ID
import co.com.mova.detail.MovieDetailActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
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

    @Inject
    lateinit var mGetMoviesUseCase: ISingleUseCase<Pair<Boolean, List<Movie>>, Int>

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        mActualPage = 1
        mView?.clear()
        getMovies()

    }

    private fun getMovies() {
        if(!mIsLoading){
            mIsLoading=true
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
        if (mHasMoreResults && !mIsLoading) {
            mActualPage++
            getMovies()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private fun onStop() {
        mDisposableBag.clear()
    }

    override fun onRefresh() {
        if (!mIsLoading) {
            mActualPage = 1
            mView?.clear()
            getMovies()

        }
    }

    override fun onClick(movie: Movie) {
        val bundle = Bundle()
        bundle.putInt(MOVIE_ID, movie.id)
        mView?.navigate(MovieDetailActivity::class.java, bundle)

    }
}