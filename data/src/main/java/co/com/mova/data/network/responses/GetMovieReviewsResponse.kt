package co.com.mova.data.network.responses

import co.com.mova.data.network.entities.APIMovieReview
import com.google.gson.annotations.SerializedName

/**
 * Created by oscarg798 on 4/23/18.
 */
data class GetMovieReviewsResponse(val id: String,
                                   @SerializedName("total_pages") val totalPages: Int,
                                   val page: Int,
                                   val results: List<APIMovieReview>)