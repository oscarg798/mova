package co.com.mova.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import co.com.mova.BaseApplication
import co.com.mova.R
import co.com.mova.data.IMAGE_URL
import co.com.mova.data.MOVIE_ID
import co.com.mova.data.YOUTUBE_BASE_URL
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity(), IMovieDetailActivityView {


    lateinit var mPresenter: IMovieDetailActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        initComponents()
    }

    override fun initComponents() {
        val presenter = MovieDetailActivityPresenter()
        (application as BaseApplication).appComponent.inject(presenter)
        mPresenter = presenter
        lifecycle.addObserver(mPresenter)
        mPresenter.bind(this)
        mIVFavorite?.setOnClickListener {
            mPresenter.troggleFavorite()
        }
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
        return intent.getIntExtra(MOVIE_ID, -1)
    }

    override fun showMovieTrailer(key: String) {
        mTVMovieTrailerLink?.text = YOUTUBE_BASE_URL + key
    }

    override fun loadMoviePoster(poster: String) {
        Picasso.get().load(IMAGE_URL + poster)
                .into(mIVMoviePoster)
    }
}
