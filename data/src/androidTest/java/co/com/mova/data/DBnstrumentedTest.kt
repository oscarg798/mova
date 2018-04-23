package co.com.mova.data

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import co.com.mova.data.local.AppDatabase
import co.com.mova.data.local.dao.GenreDAO
import co.com.mova.data.local.dao.MovieCastDAO
import co.com.mova.data.local.dao.MovieDAO
import co.com.mova.data.local.dao.MovieReviewDAO
import co.com.mova.data.local.entities.DBGenre
import co.com.mova.data.local.entities.DBMovie
import co.com.mova.data.local.entities.DBMovieCast
import co.com.mova.data.local.entities.DBMovieReview
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(AndroidJUnit4::class)
class DBnstrumentedTest {


    lateinit var mDb: AppDatabase

    lateinit var mGenreDAO: GenreDAO

    lateinit var mMovieDAO: MovieDAO

    lateinit var mMovieReviewDAO: MovieReviewDAO

    lateinit var mMovieCastDAO: MovieCastDAO

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getTargetContext()
        mDb = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        mGenreDAO = mDb.genreDAO()
        mMovieDAO = mDb.movieDAO()
        mMovieReviewDAO = mDb.movieReviewDAO()
        mMovieCastDAO = mDb.movieCastDAO()
    }


    @Test
    fun shouldInsertGenre() {
        val genre = DBGenre(1, "h")
        mGenreDAO.insert(genre)
        Assert.assertEquals(mGenreDAO.get(genre.id), genre)
    }

    @Test
    fun shouldInsertMovie() {
        val movie = DBMovie(1, 1, 1f, "h", 1f, "d",
                arrayListOf(1, 2, 3), "d", "123")
        mMovieDAO.insert(movie)
        Assert.assertEquals(mMovieDAO.get(1), movie)
    }

    @Test
    fun shouldGetFavorites() {
        val movie = DBMovie(1, 1, 1f, "h", 1f, "d",
                arrayListOf(1, 2, 3), "d", "123", true)
        mMovieDAO.insert(movie)
        Assert.assertEquals(mMovieDAO.getFavorites().size, 1)
    }

    @Test
    fun shouldMakeFavorite() {
        val movie = DBMovie(2, 1, 1f, "h", 1f, "d",
                arrayListOf(1, 2, 3), "d", "123")
        mMovieDAO.insert(movie)
        mMovieDAO.makeMovieFavorite(2)
        Assert.assertEquals(mMovieDAO.getFavorites().size, 1)
    }

    @Test
    fun shouldInsertAndGetMovieCast() {
        val dbMovieCast = DBMovieCast(1, "Oscar", "asdasd", "Gallon", arrayListOf(1))
        mMovieCastDAO.insert(dbMovieCast)
        Assert.assertEquals(1, mMovieCastDAO.getAll().size)
    }

    @Test
    fun shouldInsertAndGetMovieReview() {
        val dbMovieReview = DBMovieReview("1", "Oscar", "Good", "https://a.com", 1)
        mMovieReviewDAO.insert(dbMovieReview)
        Assert.assertEquals(dbMovieReview.id, mMovieReviewDAO.getByMovie(1)[0].id)
    }

}
