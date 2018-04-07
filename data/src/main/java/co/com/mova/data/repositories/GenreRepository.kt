package co.com.mova.data.repositories

import co.com.mova.data.local.dao.GenreDAO
import co.com.mova.data.local.entities.DBGenre
import co.com.mova.data.network.entities.APIGenre
import co.com.mova.data.network.routes.IGenreRoute
import io.reactivex.Observable
import javax.inject.Inject


/**
 * Created by oscarg798 on 4/7/18.
 */
class GenreRepository : IGenreRepository {

    @Inject
    lateinit var mGenreRoute: IGenreRoute

    @Inject
    lateinit var mGenreDAO: GenreDAO


    override fun getGenresFromAPI(): Observable<List<APIGenre>> {
        return mGenreRoute.get().map { it.genres }
    }


    override fun getGenre(id: Int): DBGenre? = mGenreDAO.get(id)

    override fun insertGenre(dbGenre: DBGenre) {
        mGenreDAO.insert(dbGenre)
    }


}