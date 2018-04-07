package co.com.mova.data.network.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by oscarg798 on 4/7/18.
 */
data class APIMovie(val id: Int,
                    @SerializedName("vote_count") val voteCount: Int,
                    @SerializedName("vote_average") val voteAverage: Float,
                    val title: String,
                    val popularity: Float,
                    @SerializedName("poster_path") val posterPath: String,
                    @SerializedName("genre_ids") val genreIds: List<Int>,
                    val overview: String,
                    @SerializedName("release_date") val releaseDate: String)