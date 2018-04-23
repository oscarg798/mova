package co.com.mova.detail

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import co.com.mova.BaseApplication
import co.com.mova.R
import co.com.mova.core.entities.Movie
import co.com.mova.data.IMAGE_URL
import co.com.mova.data.MOVIE_ID
import co.com.mova.detail.cast.CastFragment
import co.com.mova.detail.info.MovieInfoFragment
import co.com.mova.detail.review.ReviewFragment
import com.google.android.youtube.player.YouTubePlayer
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_detail.*
import java.text.SimpleDateFormat
import java.util.*

class MovieDetailActivity : AppCompatActivity(), IMovieDetailActivityView {


    lateinit var mPresenter: IMovieDetailActivityPresenter

    private var mYoutubePlayer: YouTubePlayer? = null

    private val mSDF = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault())

    private val mDateParser = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

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

        setSupportActionBar(mToolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        mIVFavorite?.setOnClickListener {
            mPresenter.troggleFavorite()
        }


//        (mYoutubeFragment as? YouTubePlayerSupportFragment)?.initialize("AIzaSyAs3J7mLfJwcUy9d5fK8OcEeITFyonFWnU", object : YouTubePlayer.OnInitializedListener {
//            override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, p1: YouTubePlayer?, p2: Boolean) {
//                mYoutubePlayer = p1
//
//
//            }
//
//            override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
//
//
//            }
//        })
    }

    override fun setUpViewPager(movie: Movie) {
        mVPMovies?.adapter = ViewPagerAdapter(supportFragmentManager,
                arrayListOf(MovieInfoFragment.newInstance(movie),
                        CastFragment.newInstance(movie.id),
                        ReviewFragment.newInstance(movie.id)),
                arrayListOf("Info", "Cast", "Review"))

        mTLMovieDetail?.setupWithViewPager(mVPMovies)

    }

    override fun showMovieRaiting(votes: Float) {
        val raiting = (5 * votes) / 100
        mRBMovieVotes?.rating = raiting
        mTVRaiting?.text = "$raiting/5"

    }

    override fun showMovieTitle(title: String) {
        mTVMovieTitle?.text = title
    }

    override fun showProgressBar() {
        mTVMovieTitle?.visibility = View.GONE
        mTVRaiting?.visibility = View.GONE
        mTVMovieReleaseDate?.visibility = View.GONE
        mRBMovieVotes?.visibility = View.GONE
        mIVMoviePoster?.visibility = View.GONE
        mTLMovieDetail?.visibility = View.GONE
        mVPMovies?.visibility = View.GONE
        mIVPlay?.visibility = View.GONE
        mPBMovieDetail?.visibility = View.VISIBLE

    }

    override fun hideProgressBar() {
        mTVMovieTitle?.visibility = View.VISIBLE
        mTVRaiting?.visibility = View.VISIBLE
        mTVMovieReleaseDate?.visibility = View.VISIBLE
        mRBMovieVotes?.visibility = View.VISIBLE
        mIVMoviePoster?.visibility = View.VISIBLE
        mTLMovieDetail?.visibility = View.VISIBLE
        mVPMovies?.visibility = View.VISIBLE
        mIVPlay?.visibility = View.VISIBLE
        mIVPlay?.visibility = View.VISIBLE
        mPBMovieDetail?.visibility = View.GONE
    }

    override fun showMovieReleaseDate(date: String) {
        mTVMovieReleaseDate?.text = "${mSDF.format(mDateParser.parse(date))}"
    }

    override fun navigate(destination: Class<*>, arguments: Bundle?) {
    }


    override fun changeFavoriteIcon(isFavorite: Boolean) {
        val id = if (isFavorite) R.drawable.ic_favorite else R.drawable.ic_favorite_border
        mIVFavorite?.setImageDrawable(ContextCompat.getDrawable(this, id))
    }

    override fun getMovieId(): Int {
        return intent.getIntExtra(MOVIE_ID, -1)
    }

    override fun showMovieTrailer(key: String) {
        mYoutubePlayer?.loadVideo(key)
    }

    override fun loadMoviePoster(poster: String) {

        Picasso.get().load(IMAGE_URL + poster)
                .into(mIVMoviePoster)
        Picasso.get().load(IMAGE_URL + poster)
                .into(mIVMoviePosterOverlay)
    }
}
