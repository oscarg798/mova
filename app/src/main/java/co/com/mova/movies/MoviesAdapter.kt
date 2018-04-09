package co.com.mova.movies

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import co.com.mova.R
import co.com.mova.core.entities.Movie
import co.com.mova.data.IMAGE_URL
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by oscarg798 on 4/7/18.
 */
class MoviesAdapter(private val mMovies: ArrayList<Movie> = ArrayList(),
                    private val mMoviesCallback: IMoviesCallback) : RecyclerView.Adapter<MovieItemViewHolder>() {

    private val mSDF = SimpleDateFormat( "MMMM dd, yyyy", Locale.getDefault())
    private val mDateParser = SimpleDateFormat("yyyy-MM-dd",Locale.getDefault())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        return MovieItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false))
    }

    override fun getItemCount(): Int {
        return mMovies.size
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        Picasso.get().load("$IMAGE_URL${mMovies[position].posterPath}")
                .into(holder.mIVMoviePoster)
        holder.mTVMovieTitle.text = mMovies[position].title
        holder.mBCPMovieVotes.setProgress(mMovies[position].voteAverage * 10)
        holder.mBCPMovieVotes.setAnimated(false)
        holder.mTVGenres.text = getMovieGenres(mMovies[position])
        holder.mTVMovieReleaseDate.text = mSDF.format(mDateParser.parse(mMovies[position].releaseDate))
        holder.itemView.setOnClickListener {
            mMoviesCallback.onClick(mMovies[holder.adapterPosition])
        }

    }

    private fun getMovieGenres(movie: Movie): String {
        var message = ""
        val genres = movie.genres.take(2)
        genres.forEach {
            message += it.name
            if (movie.genres.indexOf(it) < 1 && genres.size > 1) {
                message += ", "
            }
        }
        return message
    }

    fun addMovies(movies: List<Movie>) {
        mMovies.addAll(movies)
        notifyDataSetChanged()
    }


    fun clear() {
        mMovies.clear()
        notifyDataSetChanged()
    }


}