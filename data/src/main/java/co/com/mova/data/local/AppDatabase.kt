package co.com.mova.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import co.com.mova.data.local.dao.GenreDAO
import co.com.mova.data.local.dao.MovieDAO
import co.com.mova.data.local.entities.DBGenre
import co.com.mova.data.local.entities.DBMovie

/**
 * Created by oscarg798 on 4/7/18.
 */
@Database(entities = [(DBGenre::class), (DBMovie::class)], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun genreDAO(): GenreDAO

    abstract fun movieDAO(): MovieDAO
}