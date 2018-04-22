package co.com.mova.movies

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.ShapeDrawable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.PopupMenu
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import co.com.mova.R
import co.com.mova.core.entities.Movie
import co.com.mova.data.IMAGE_URL
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by oscarg798 on 4/7/18.
 */
class MoviesAdapter(private val mMovies: ArrayList<Movie> = ArrayList(),
                    private val mMoviesCallback: IMoviesCallback) : RecyclerView.Adapter<MovieItemViewHolder>() {

    private val mSDF = SimpleDateFormat("MMM ''yy", Locale.getDefault())
    private val mDateParser = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        return MovieItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false))
    }

    override fun getItemCount(): Int {
        return mMovies.size
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        holder.mIVMoviePoster?.loadImage("$IMAGE_URL${mMovies[position].posterPath}")
        holder.mTVMovieTitle?.text = mMovies[position].title
        holder.mRBMovieVotes?.rating = calculateRating(mMovies[position].voteAverage * 10)
        holder.mTVGenres?.text = getMovieGenres(mMovies[position])
        holder.mTVMovieReleaseDate?.text = formatMovieReleaseDate(mMovies[position])

        (holder.mIVFavorite?.background as? GradientDrawable)?.setColor( if (mMovies[position].favorite)
            ContextCompat.getColor(holder.itemView.context, R.color.colorAccent) else Color.GRAY)

        holder.mIVOptions?.setOnClickListener {
            val popUp = PopupMenu(it.context, it)
            popUp.inflate(R.menu.movie_item_menu)
            popUp.show()
        }

        holder.itemView?.setOnClickListener {
            if (it.id != R.id.mIVOptions) {
                mMoviesCallback.onClick(mMovies[holder.adapterPosition])
            }

        }

    }

    private fun formatMovieReleaseDate(movie: Movie): String {
        var releaseDate = mSDF.format(mDateParser.parse(movie.releaseDate))
        if (releaseDate.isNotEmpty()) {
            releaseDate = "${releaseDate[0].toUpperCase()}${releaseDate.subSequence(1, releaseDate.length)}"
        }

        return releaseDate
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

    private fun calculateRating(votes: Float): Float {
        return (5 * votes) / 100
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