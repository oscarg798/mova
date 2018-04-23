package co.com.mova.data.local.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by oscarg798 on 4/23/18.
 */
@Entity(tableName = "movie_review")
data class DBMovieReview(@PrimaryKey val id: String,
                         val author: String,
                         val content: String,
                         val url: String,
                         val movieId: Int)