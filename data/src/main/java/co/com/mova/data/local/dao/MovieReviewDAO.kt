package co.com.mova.data.local.dao

import android.arch.persistence.room.*
import co.com.mova.data.local.entities.DBMovieReview

/**
 * Created by oscarg798 on 4/23/18.
 */
@Dao
interface MovieReviewDAO {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(dbMovieReview: DBMovieReview)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun update(dbMovieReview: DBMovieReview)

    @Query("select * from movie_review where id=:id")
    fun get(id: Int): DBMovieReview?

    @Query("select * from movie_review where movieId=:movieId")
    fun getByMovie(movieId: Int): List<DBMovieReview>

}