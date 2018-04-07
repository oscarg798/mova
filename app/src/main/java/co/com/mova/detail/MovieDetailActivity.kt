package co.com.mova.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import co.com.mova.R
import co.com.mova.data.MOVIE_ID
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity(), IMovieDetailActivityView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
    }

    override fun initComponents() {

    }

    override fun showProgressBar() {
        mCLMovieDetail?.visibility = View.GONE
        mPBMovieDetail?.visibility = View.VISIBLE
    }

    override fun showMovieTitle(title: String) {
        supportActionBar?.title = title
    }

    override fun hideProgressBar() {
        mCLMovieDetail?.visibility = View.VISIBLE
        mPBMovieDetail?.visibility = View.GONE
    }

    override fun showMovieReleaseDate(date: String) {
        mTVMovieReleaseDate?.text = "Release: $date"
    }

    override fun navigate(destination: Class<*>, arguments: Bundle?) {
    }

    override fun showMovieOverview(overView: String) {
        mTVMovieOverview?.text = overView
    }

    override fun changeFavoriteIcon(isFavorite: Boolean) {
        val id = if (isFavorite) R.drawable.ic_favorite else R.drawable.ic_favorite_border
        mIVFavorite?.setImageDrawable(ContextCompat.getDrawable(this, id))
    }

    override fun getMovieId(): Int {
        return intent.getIntExtra(MOVIE_ID, 0)
    }
}
