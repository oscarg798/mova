package co.com.mova.data.network.routes

import co.com.mova.data.API_KEY
import co.com.mova.data.network.responses.GetMovieCreditsResponse
import co.com.mova.data.network.responses.GetMovieReviewsResponse
import co.com.mova.data.network.responses.GetMovieVideoResponse
import co.com.mova.data.network.responses.GetMoviesResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by oscarg798 on 4/7/18.
 */
interface IMovieRoute {

    @GET("movie/popular")
    fun getPopularMovies(@Query("page") page: Int,
                         @Query("api_key") key: String = API_KEY): Observable<GetMoviesResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovies(@Query("page") page: Int,
                          @Query("api_key") key: String = API_KEY): Observable<GetMoviesResponse>

    @GET("movie/upcoming")
    fun getUpcomingMovies(@Query("page") page: Int,
                          @Query("api_key") key: String = API_KEY): Observable<GetMoviesResponse>

    @GET("movie/{id}/videos")
    fun getMovieVideos(@Path("id") id: Int,
                       @Query("api_key") key: String = API_KEY): Observable<GetMovieVideoResponse>

    @GET("movie/{movie_id}/credits")
    fun getMovieCredits(@Path("movie_id") movie_id: Int,
                        @Query("api_key") key: String = API_KEY): Observable<GetMovieCreditsResponse>

    @GET("movie/{movie_id}/reviews")
    fun getMovieReview(@Path("movie_id") movie_id: Int,
                       @Query("api_key") key: String = API_KEY): Observable<GetMovieReviewsResponse>
}