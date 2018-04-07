package co.com.mova.data.repositories

import co.com.mova.data.di.DataComponent

/**
 * Created by oscarg798 on 4/7/18.
 */
interface IRepositoryFactory{

    val mDataComponent: DataComponent?

    val mGenreRepository: IGenreRepository

    val mMovieRepository: IMovieRepository

}