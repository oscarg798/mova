package co.com.mova.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import co.com.mova.data.local.dao.GenreDAO
import co.com.mova.data.local.dao.MovieCastDAO
import co.com.mova.data.local.dao.MovieDAO
import co.com.mova.data.local.dao.MovieReviewDAO
import co.com.mova.data.local.entities.DBGenre
import co.com.mova.data.local.entities.DBMovie
import co.com.mova.data.local.entities.DBMovieCast
import co.com.mova.data.local.entities.DBMovieReview

/**
 * Created by oscarg798 on 4/7/18.
 */
@Database(entities = [(DBGenre::class), (DBMovie::class), (DBMovieCast::class),
    (DBMovieReview::class)], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun genreDAO(): GenreDAO

    abstract fun movieDAO(): MovieDAO

    abstract fun movieReviewDAO(): MovieReviewDAO

    abstract fun movieCastDAO(): MovieCastDAO
}