package co.com.mova.data.repositories

import co.com.mova.data.local.entities.DBGenre
import co.com.mova.data.network.entities.APIGenre
import io.reactivex.Observable

/**
 * Created by oscarg798 on 4/7/18.
 */
interface IGenreRepository {
    fun getGenresFromAPI(): Observable<List<APIGenre>>

    fun getGenre(id: Int): DBGenre?

    fun insertGenre(dbGenre: DBGenre)
}
