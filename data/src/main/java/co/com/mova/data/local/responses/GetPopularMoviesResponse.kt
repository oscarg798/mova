package co.com.mova.data.local.responses

import co.com.mova.data.network.entities.APIMovie
import com.google.gson.annotations.SerializedName

/**
 * Created by oscarg798 on 4/7/18.
 */
data class GetPopularMoviesResponse(val page: Int,
                                    @SerializedName("total_results") val totalResults: Int,
                                    @SerializedName("total_pages") val totalPages: Int,
                                    val results: List<APIMovie>)