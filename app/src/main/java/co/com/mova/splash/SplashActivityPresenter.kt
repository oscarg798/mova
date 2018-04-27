package co.com.mova.splash

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import co.com.mova.core.entities.Genre
import co.com.mova.core.use_cases.base.ISingleUseCase
import co.com.mova.dashboard.DashboardActivity
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
                if(t.isEmpty()){
                    showErrorMessage()
                }else{
                    mView?.navigate(DashboardActivity::class.java, null, null)

                }
                mDisposableBag.remove(this)
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
                showErrorMessage()
                mDisposableBag.remove(this)
            }
        }
        mDisposableBag.add(disposable)
        mGetGenresUseCase.execute(null, disposable)

    }


    private fun showErrorMessage(){
        mView?.showMessage("Please verify your internet connection and  try again")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        mDisposableBag.clear()
    }
}