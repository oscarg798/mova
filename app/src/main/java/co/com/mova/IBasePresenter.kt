package co.com.post

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent

/**
 * Created by oscarg798 on 4/7/18.
 */
interface IBasePresenter<T : IBaseView> : LifecycleObserver {
    var mView: T?

    fun bind(view: T) {
        mView = view
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun unBind() {
        mView = null
    }

}