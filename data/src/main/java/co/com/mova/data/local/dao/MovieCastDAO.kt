package co.com.mova.data.local.dao

import android.arch.persistence.room.*
import co.com.mova.data.local.entities.DBMovieCast

/**
 * Created by oscarg798 on 4/23/18.
 */
@Dao
interface MovieCastDAO{

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(dbMovieCast: DBMovieCast)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun update(dbMovieCast: DBMovieCast)

    @Query("select * from movie_cast where id=:id")
    fun get(id: Int): DBMovieCast?

    @Query("select * from movie_cast")
    fun getAll(): List<DBMovieCast>

}