package co.com.mova.data.network.responses

import co.com.mova.data.network.entities.APIMovieCast

/**
 * Created by oscarg798 on 4/23/18.
 */
data class GetMovieCreditsResponse(val id: Int,
                                   val cast: List<APIMovieCast>)