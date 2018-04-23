package co.com.mova.data.local.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by oscarg798 on 4/23/18.
 */
@Entity(tableName = "movie_cast")
data class DBMovieCast(@PrimaryKey val id: Int,
                       val name: String,
                       val profilePath: String?,
                       val character: String,
                       val movieIds: List<Int>)