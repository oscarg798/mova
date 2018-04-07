package co.com.mova.data.network.responses

import co.com.mova.data.network.entities.ApiMovieVideo

/**
 * Created by oscarg798 on 4/7/18.
 */
data class GetMovieVideoResponse(val id: Int,
                                 val results: List<ApiMovieVideo>)