package co.com.mova.di

import android.content.Context
import co.com.mova.core.entities.Genre
import co.com.mova.core.entities.Movie
import co.com.mova.core.entities.MovieCast
import co.com.mova.core.entities.MovieReview
import co.com.mova.core.use_cases.IUseCaseFactory
import co.com.mova.core.use_cases.UseCaseFactory
import co.com.mova.core.use_cases.base.ICompletableUseCase
import co.com.mova.core.use_cases.base.ISingleUseCase
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

/**
 * Created by oscarg798 on 4/7/18.
 */
@Singleton
@Module
class UseCaseModule {

    @Provides
    @Singleton
    fun providesUseCaseFactory(context: Context): IUseCaseFactory = UseCaseFactory(context)

    @Provides
    @Singleton
    fun providesGenresUseCase(useCaseFactory: IUseCaseFactory): ISingleUseCase<List<Genre>, Any?> {
        return useCaseFactory.getGenresUseCase(Schedulers.io(), AndroidSchedulers.mainThread())

    }

    @Provides
    @Singleton
    fun providesMovieGenresUseCase(useCaseFactory: IUseCaseFactory):
            ISingleUseCase<List<Genre>, List<Int>> {
        return useCaseFactory.getMovieGenresUseCase(Schedulers.io(), AndroidSchedulers.mainThread())
    }

    @Provides
    @Singleton
    fun providesFavoriteMoviesUseCase(useCaseFactory: IUseCaseFactory):
            ISingleUseCase<List<Movie>, Any?> {
        return useCaseFactory.getFavoriteMoviesUseCase(Schedulers.io(), AndroidSchedulers.mainThread())
    }

    @Provides
    @Singleton
    fun providesMoviesUseCase(useCaseFactory: IUseCaseFactory):
            ISingleUseCase<Pair<Boolean, List<Movie>>, Int> {
        return useCaseFactory.getMoviesUseCase(Schedulers.io(), AndroidSchedulers.mainThread())
    }

    @Provides
    @Singleton
    fun providesMovieUseCase(useCaseFactory: IUseCaseFactory):
            ISingleUseCase<Movie, Int> {
        return useCaseFactory.getMovieUseCase(Schedulers.io(), AndroidSchedulers.mainThread())
    }


    @Provides
    @Singleton
    fun providesMakeMovieFavoriteUseCase(useCaseFactory: IUseCaseFactory):
            ICompletableUseCase<Pair<Int,Boolean>> {
        return useCaseFactory.getMakeMovieFavoriteUseCase(Schedulers.io(), AndroidSchedulers.mainThread())
    }

    @Provides
    @Singleton
    fun providesGetMovieVideoUseCase(useCaseFactory: IUseCaseFactory):
            ISingleUseCase<String, Int> {
        return useCaseFactory.getMovieVideosUseCase(Schedulers.io(), AndroidSchedulers.mainThread())
    }

    @Provides
    @Singleton
    fun providesGetMovieCastUseCase(useCaseFactory: IUseCaseFactory):ISingleUseCase<List<MovieCast>,Int>{
        return useCaseFactory.getMovieCastUseCase(Schedulers.io(),AndroidSchedulers.mainThread())
    }

    @Provides
    @Singleton
    fun providesGeTMovieReviewUseCase(useCaseFactory: IUseCaseFactory):ISingleUseCase<List<MovieReview>,Int>{
        return useCaseFactory.getMovieReviewsUseCase(Schedulers.io(),AndroidSchedulers.mainThread())
    }
}