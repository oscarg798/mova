package co.com.mova.core.di

import android.content.Context
import co.com.mova.data.repositories.IGenreRepository
import co.com.mova.data.repositories.IMovieRepository
import co.com.mova.data.repositories.IRepositoryFactory
import co.com.mova.data.repositories.RepositoryFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by oscarg798 on 4/7/18.
 */
@Singleton
@Module
class RepositoryModule(private val mContext: Context) {


    @Provides
    @Singleton
    fun provideContext(): Context = mContext

    @Provides
    @Singleton
    fun providesRepositoryFactory(context: Context): IRepositoryFactory {
        return RepositoryFactory(context)
    }

    @Provides
    @Singleton
    fun providesGenreRepository(repositoryFactory: IRepositoryFactory): IGenreRepository {
        return repositoryFactory.mGenreRepository
    }

    @Provides
    @Singleton
    fun providesMovieRepository(repositoryFactory: IRepositoryFactory):IMovieRepository{
        return repositoryFactory.mMovieRepository
    }

}