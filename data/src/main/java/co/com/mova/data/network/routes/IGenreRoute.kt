package co.com.mova.data.network.routes

import co.com.mova.data.API_KEY
import co.com.mova.data.local.responses.GetGenreResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by oscarg798 on 4/7/18.
 */
interface IGenreRoute {

    @GET("genre/movie/list")
    fun get(@Query("api_key") key: String = API_KEY): Observable<GetGenreResponse>
}