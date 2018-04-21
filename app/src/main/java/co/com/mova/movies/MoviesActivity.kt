package co.com.mova.movies

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import co.com.mova.BaseApplication
import co.com.mova.EndLessScrollListener
import co.com.mova.R
import co.com.mova.core.entities.Movie
import kotlinx.android.synthetic.main.activity_movies.*
import android.support.animation.DynamicAnimation
import android.support.animation.SpringAnimation
import android.support.animation.SpringForce
import android.support.animation.SpringForce.*
import android.view.GestureDetector
import android.view.MotionEvent


class MoviesActivity : AppCompatActivity(), IMoviesActivityView {

    lateinit var mPresenter: IMoviesActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)
        initComponents()
    }

    override fun initComponents() {
        val presenter = MoviesActivityPresenter()
        (application as BaseApplication).appComponent.inject(presenter)
        mPresenter = presenter
        lifecycle.addObserver(presenter)
        mPresenter.bind(this)
        setSupportActionBar(mToolbar)
        mRVMovies?.setHasFixedSize(false)
        mRVMovies?.layoutManager = LinearLayoutManager(this)
        mRVMovies?.adapter = MoviesAdapter(mMoviesCallback = mPresenter)
        mRVMovies?.addOnScrollListener(EndLessScrollListener(mPresenter))
        mSRLMovies?.setOnRefreshListener(mPresenter)
        mSRLMovies?.isEnabled = false

        val g = GestureDetector(this, object : GestureDetector.OnGestureListener {
            override fun onShowPress(e: MotionEvent?) {

            }

            override fun onSingleTapUp(e: MotionEvent?): Boolean {
                return false
            }

            override fun onDown(e: MotionEvent?): Boolean {
                return false
            }

            override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
                mRVMovies?.let {
                    if(velocityY>0){
                        val initPost = it.y
                        val anim = SpringAnimation(it, DynamicAnimation.TRANSLATION_Y, 0f)
                        val force = SpringForce()
                        force.dampingRatio = DAMPING_RATIO_MEDIUM_BOUNCY
                        force.stiffness = STIFFNESS_MEDIUM
                        anim.spring = force
                        anim.animateToFinalPosition(100f)
                        anim.addEndListener(object : DynamicAnimation.OnAnimationEndListener {
                            override fun onAnimationEnd(animation: DynamicAnimation<out DynamicAnimation<*>>?, canceled: Boolean, value: Float, velocity: Float) {
                                anim.removeEndListener(this)
                                anim.animateToFinalPosition(initPost)
                            }
                        })
                    }



                }
                return true
            }

            override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
                return false
            }

            override fun onLongPress(e: MotionEvent?) {
            }
        })

        mRVMovies?.setOnTouchListener { _, event -> g.onTouchEvent(event) }


    }

    override fun showProgressBar() {
        mSRLMovies?.isRefreshing = true
    }

    override fun hideProgressBar() {
        mSRLMovies?.isRefreshing = false
    }

    override fun showMovies(movies: List<Movie>) {
        mRVMovies?.adapter?.let {
            (it as MoviesAdapter).addMovies(movies)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.let {
            val isShowingFavorites = mPresenter.isShowingFavorites()
            val resourceId = if (isShowingFavorites) R.drawable.ic_favorite_border_white
            else R.drawable.ic_favorite_white
            item.icon = ContextCompat.getDrawable(this@MoviesActivity, resourceId)
            mPresenter.toggleFavorites()

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menu?.let {
            menuInflater.inflate(R.menu.movies_menu, menu)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun navigate(destination: Class<*>, arguments: Bundle?) {
        val intent = Intent(this, destination)
        arguments?.let {
            intent.putExtras(arguments)
        }
        startActivity(intent)
    }

    override fun clear() {
        mRVMovies?.adapter?.let {
            (it as MoviesAdapter).clear()
        }
    }
}
