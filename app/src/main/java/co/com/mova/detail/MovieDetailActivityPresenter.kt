package co.com.mova.detail

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import android.text.TextUtils
import co.com.mova.core.entities.Movie
import co.com.mova.core.use_cases.base.ICompletableUseCase
import co.com.mova.core.use_cases.base.ISingleUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by oscarg798 on 4/7/18.
 */
class MovieDetailActivityPresenter : IMovieDetailActivityPresenter {


    override var mView: IMovieDetailActivityView? = null

    private val mDisposableBag = CompositeDisposable()

    private var mMovie: Movie? = null

    @Inject
    lateinit var mGetMovieVideoUseCase: ISingleUseCase<String, Int>

    @Inject
    lateinit var mGetMovieUseCase: ISingleUseCase<Movie, Int>

    @Inject
    lateinit var mToggleMovieFavoriteUseCase: ICompletableUseCase<Pair<Int, Boolean>>

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun getMovieDetails() {
        mView?.showProgressBar()
        val id = mView?.getMovieId() ?: -1
        if (id > 0) {
            mGetMovieUseCase.getUseCase(id)
                    .zipWith(mGetMovieVideoUseCase.getUseCase(id),
                            BiFunction<Movie, String, Pair<Movie, String>> { t1, t2 ->
                                Pair(t1, t2)
                            })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ (movie, trailerKey) ->
                        mMovie = movie
                        mView?.showMovieTitle(movie.title)
                        mView?.showMovieOverview(movie.overview)
                        mView?.showMovieReleaseDate(movie.releaseDate)
                        mView?.changeFavoriteIcon(movie.favorite)
                        mView?.loadMoviePoster(movie.posterPath)
                        if (!TextUtils.isEmpty(trailerKey)) {
                            mView?.showMovieTrailer(trailerKey)
                        }
                        mView?.hideProgressBar()

                    }, { e ->
                        e.printStackTrace()

                    })


        }

    }

    override fun troggleFavorite() {
        mMovie?.let {
            mView?.showProgressBar()
            val disposable = object : DisposableCompletableObserver() {
                override fun onComplete() {
                    it.favorite = !it.favorite
                    mView?.changeFavoriteIcon(it.favorite)
                    mDisposableBag.remove(this)
                    mView?.hideProgressBar()
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    mDisposableBag.remove(this)
                    mView?.hideProgressBar()
                }
            }
            mDisposableBag.add(disposable)
            mToggleMovieFavoriteUseCase.execute(Pair(it.id, !it.favorite), disposable)
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        mDisposableBag.clear()
    }
}