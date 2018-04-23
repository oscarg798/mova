package co.com.mova.detail.cast

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import co.com.mova.core.entities.MovieCast
import co.com.mova.core.use_cases.base.ISingleUseCase
import co.com.mova.data.MOVIE_ID
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

/**
 * Created by oscarg798 on 4/23/18.
 */
class CastFragmentPresenter : ICastFragmentPresenter {

    override var mView: ICastFragmentView? = null

    private val mDisposableBag = CompositeDisposable()

    @Inject
    lateinit var mGetMovieCastUseCase: ISingleUseCase<List<MovieCast>, Int>

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun getCast() {
        mView?.getArguments()?.getInt(MOVIE_ID)?.let {
            mView?.showProgressBar()
            mView?.clear()
            val disposable = object : DisposableSingleObserver<List<MovieCast>>() {
                override fun onSuccess(t: List<MovieCast>) {
                    mView?.showCast(t)
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
            mGetMovieCastUseCase.execute(it, disposable)
        }


    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        mDisposableBag.clear()
    }

}