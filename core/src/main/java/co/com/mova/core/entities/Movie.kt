package co.com.mova.core.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by oscarg798 on 4/7/18.
 */
data class Movie(val id: Int,
                 val voteCount: Int,
                 val voteAverage: Float,
                 val title: String,
                 val popularity: Float,
                 val posterPath: String,
                 val genres: ArrayList<Genre>,
                 val overview: String,
                 val releaseDate: String,
                 var favorite: Boolean)