package co.com.mova.core.use_cases.base

import io.reactivex.observers.DisposableObserver

/**
 * Created by oscarg798 on 4/7/18.
 */
interface IObservableUseCase<Response, Params>{

    fun execute(params: Params, observer: DisposableObserver<Response>)

    fun dispose()
}