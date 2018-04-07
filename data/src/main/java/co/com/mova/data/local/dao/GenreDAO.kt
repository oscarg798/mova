package co.com.mova.data.local.dao

import android.arch.persistence.room.*
import co.com.mova.data.local.entities.DBGenre

/**
 * Created by oscarg798 on 4/7/18.
 */
@Dao
interface GenreDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(dbGenre: DBGenre)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun update(dbGenre: DBGenre)

    @Query("select * from genre where id=:id")
    fun get(id: Int): DBGenre?

    @Query("select * from genre")
    fun getAll(): List<DBGenre>


}