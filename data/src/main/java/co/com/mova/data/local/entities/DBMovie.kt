package co.com.mova.data.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by oscarg798 on 4/7/18.
 */
@Entity(tableName = "movie")
data class DBMovie(@PrimaryKey val id: Int,
                   val voteCount: Int,
                   val voteAverage: Float,
                   val title: String,
                   val popularity: Float,
                   val posterPath: String,
                   val genreIds: List<Int>,
                   val overview: String,
                   val releaseDate: String)