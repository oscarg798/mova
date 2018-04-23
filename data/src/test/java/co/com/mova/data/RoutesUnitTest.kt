package co.com.mova.data

import android.content.Context
import android.test.mock.MockContext
import it.cosenonjaviste.daggermock.DaggerMock
import it.cosenonjaviste.daggermock.InjectFromComponent
import co.com.mova.data.di.DataComponent
import co.com.mova.data.di.DatabaseModule
import co.com.mova.data.di.NetworkModule
import co.com.mova.data.di.RoutesModule
import co.com.mova.data.network.responses.GetGenreResponse
import co.com.mova.data.network.responses.GetMovieCreditsResponse
import co.com.mova.data.network.responses.GetMovieReviewsResponse
import co.com.mova.data.network.responses.GetPopularMoviesResponse
import co.com.mova.data.network.routes.IGenreRoute
import co.com.mova.data.network.routes.IMovieRoute
import io.reactivex.observers.TestObserver

import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class RoutesUnitTest {

    @get:Rule
    val rule = DaggerMock.rule<DataComponent>(NetworkModule(), DatabaseModule(MockContext()), RoutesModule())


    @InjectFromComponent
    lateinit var retrofit: Retrofit

    @InjectFromComponent
    lateinit var genreRoute: IGenreRoute

    @InjectFromComponent
    lateinit var mMovieRoute: IMovieRoute


    @Test
    fun shouldGetGenresWithoutErrors() {
        val subscriber = TestObserver<GetGenreResponse>()
        genreRoute.get().subscribeWith(subscriber)
        subscriber.assertNoErrors()
                .assertValueCount(1)
                .assertComplete()
    }

    @Test
    fun shouldGetMoviesWithoutErrors() {
        val subs = TestObserver<GetPopularMoviesResponse>()
        mMovieRoute.getPopularMovies(1).subscribeWith(subs)
        subs.assertNoErrors()
                .assertValueCount(1)
                .assertComplete()
    }

    @Test
    fun shouldGetMovieCreditsWithoutErrors() {
        val subs = TestObserver<GetMovieCreditsResponse>()
        mMovieRoute.getMovieCredits(157336).subscribeWith(subs)
        subs.assertNoErrors()
                .assertValueCount(1)
                .assertComplete()
    }

    @Test
    fun shouldGetMovieReviewsWithoutErrors() {
        val subs = TestObserver<GetMovieReviewsResponse>()
        mMovieRoute.getMovieReview(157336).subscribeWith(subs)
        subs.assertNoErrors()
                .assertValueCount(1)
                .assertComplete()
    }


}