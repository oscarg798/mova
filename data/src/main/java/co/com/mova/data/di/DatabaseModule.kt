package co.com.mova.data.di

import android.arch.persistence.room.Room
import android.content.Context
import co.com.mova.data.DATABASE_NAME
import co.com.mova.data.local.AppDatabase
import co.com.mova.data.local.dao.GenreDAO
import co.com.mova.data.local.dao.MovieDAO
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by oscarg798 on 4/7/18.
 */
@Module
@Singleton
class DatabaseModule(private val mContext: Context){

    @Provides
    fun providesContext(): Context = mContext


    @Provides
    fun providesDatabase(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).build()

    @Provides
    fun providesGenreDAO(appDatabase: AppDatabase):GenreDAO = appDatabase.genreDAO()

    @Provides
    fun providesMovieDAO(appDatabase: AppDatabase):MovieDAO = appDatabase.movieDAO()



}