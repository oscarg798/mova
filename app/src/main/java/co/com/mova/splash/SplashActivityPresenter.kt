package co.com.mova.splash

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import co.com.mova.MoviesActivity
import co.com.mova.core.entities.Genre
import co.com.mova.core.use_cases.base.ISingleUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

/**
 * Created by oscarg798 on 4/7/18.
 */
class SplashActivityPresenter : ISplashActivityPresenter {

    override var mView: ISplashActivityView? = null

    private val mDisposableBag = CompositeDisposable()

    @Inject
    lateinit var mGetGenresUseCase: ISingleUseCase<List<Genre>, Any?>

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun getGenres() {
        val disposable = object : DisposableSingleObserver<List<Genre>>() {
            override fun onSuccess(t: List<Genre>) {
                mView?.navigate(MoviesActivity::class.java, null)
                mDisposableBag.remove(this)
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
                mDisposableBag.remove(this)
            }
        }
        mDisposableBag.add(disposable)
        mGetGenresUseCase.execute(null, disposable)

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        mDisposableBag.clear()
    }
}