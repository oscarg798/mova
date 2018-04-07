package co.com.mova.core.use_cases.base

import io.reactivex.Single
import io.reactivex.observers.DisposableSingleObserver

/**
 * Created by oscarg798 on 4/7/18.
 */
interface ISingleUseCase<Response, Params>{

    fun getUseCase(params: Params): Single<Response>

    fun execute(params: Params, observer: DisposableSingleObserver<Response>)

    fun dispose()
}