package co.com.mova.detail.review

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import co.com.mova.core.entities.MovieReview
import co.com.mova.core.use_cases.base.ISingleUseCase
import co.com.mova.data.MOVIE_ID
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

/**
 * Created by oscarg798 on 4/23/18.
 */
class ReviewFragmentPresenter : IReviewFragmentPresenter {


    override var mView: IReviewFragmentView? = null

    private val mDisposableBag = CompositeDisposable()

    @Inject
    lateinit var mGetReviewsUseCase: ISingleUseCase<List<MovieReview>, Int>

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun getMoviesReviews() {
        mView?.getArguments()?.getInt(MOVIE_ID)?.let {
            mView?.clear()
            mView?.showProgressBar()
            val disposable = object : DisposableSingleObserver<List<MovieReview>>() {
                override fun onSuccess(t: List<MovieReview>) {
                    mView?.showReviews(t)
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
            mGetReviewsUseCase.execute(it, disposable)
        }


    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {

    }
}