package co.com.mova.core.use_cases.base

import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableCompletableObserver

/**
 * Created by oscarg798 on 4/7/18.
 */
abstract class CompletableUseCase<Params>(private val mSubscribeOnScheduler: Scheduler,
                                          private val mObserverOnScheduler: Scheduler) :
        ICompletableUseCase<Params> {

    private val mCompositeDisposables = CompositeDisposable()

    abstract fun buildUseCase(params: Params): Completable

    override fun execute(params: Params, observer: DisposableCompletableObserver) {
        val observable = buildUseCase(params)
                .subscribeOn(mSubscribeOnScheduler)
                .observeOn(mObserverOnScheduler)

        mCompositeDisposables.add(observable.subscribeWith(observer))
    }


    override fun getUseCase(params: Params): Completable {
        return buildUseCase(params)
    }

    override fun dispose() {
        mCompositeDisposables.dispose()
        mCompositeDisposables.clear()
    }
}