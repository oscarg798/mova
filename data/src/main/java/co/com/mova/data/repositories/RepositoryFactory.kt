package co.com.mova.data.repositories

import android.content.Context
import co.com.mova.data.di.*

/**
 * Created by oscarg798 on 4/7/18.
 */
class RepositoryFactory(mContext: Context) : IRepositoryFactory {
    override val mDataComponent: DataComponent? =
            DaggerDataComponent.builder()
                    .networkModule(NetworkModule())
                    .databaseModule(DatabaseModule(mContext))
                    .routesModule(RoutesModule())
                    .build()


    override val mGenreRepository: IGenreRepository?
        get()  {
            val repo = GenreRepository()
            mDataComponent?.inject(repo)
            return repo
        }
    override val mMovieRepository: IMovieRepository?
        get() {
            val repo = MovieRepository()
            mDataComponent?.inject(repo)
            return repo

        }
}