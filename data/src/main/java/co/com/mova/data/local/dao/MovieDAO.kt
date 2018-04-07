package co.com.mova.data.local.dao

import android.arch.persistence.room.*
import co.com.mova.data.local.entities.DBMovie

/**
 * Created by oscarg798 on 4/7/18.
 */
@Dao
interface MovieDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(dbMovie: DBMovie)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun update(dbMovie: DBMovie)

    @Query("select * from movie where id=:id")
    fun get(id: Int): DBMovie?

    @Query("select * from movie")
    fun getAll(): List<DBMovie>

    @Query("select * from movie where favorite = 1")
    fun getFavorites(): List<DBMovie>

    @Query("update movie set favorite=1 where id=:id")
    fun makeMovieFavorite(id: Int)

    @Query("update movie set favorite=0 where id=:id")
    fun removeMovieFromFavorite(id: Int)
}