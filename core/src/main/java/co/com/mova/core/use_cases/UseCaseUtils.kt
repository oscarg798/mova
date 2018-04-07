package co.com.mova.core.use_cases

import co.com.mova.core.entities.Genre
import co.com.mova.data.repositories.IGenreRepository

/**
 * Created by oscarg798 on 4/7/18.
 */
class UseCaseUtils {


    fun getMovieGenres(params: List<Int>, repository: IGenreRepository): ArrayList<Genre> {
        val genres = ArrayList<Genre>()
        params.forEach {
            repository.getGenre(it)?.let {
                genres.add(Genre(it.id, it.name))
            }

        }

        return genres
    }


    private object Holder {
        val INSTACE = UseCaseUtils()
    }

    companion object {
        val instance by lazy {
            Holder.INSTACE
        }
    }


}