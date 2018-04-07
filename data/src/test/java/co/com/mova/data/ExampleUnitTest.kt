package co.com.mova.data

import it.cosenonjaviste.daggermock.DaggerMock
import it.cosenonjaviste.daggermock.InjectFromComponent
import co.com.mova.data.di.DataComponent
import co.com.mova.data.di.NetworkModule
import co.com.mova.data.di.RoutesModule
import co.com.mova.data.network.responses.GetGenreResponse
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
class ExampleUnitTest {

    @get:Rule
    val rule = DaggerMock.rule<DataComponent>(NetworkModule(), RoutesModule())


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


}