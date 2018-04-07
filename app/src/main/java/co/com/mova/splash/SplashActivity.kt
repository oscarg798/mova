package co.com.mova.splash

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import co.com.mova.R

class SplashActivity : AppCompatActivity(), ISplashActivityView {

    private val mPresenter: ISplashActivityPresenter = SplashActivityPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        lifecycle.addObserver(mPresenter)
        mPresenter.bind(this)
    }


    override fun initComponents() {
    }

    override fun showProgressBar() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideProgressBar() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun navigate(destination: Class<*>, arguments: Bundle?) {
        val intent = Intent(this, destination)
        arguments?.let {
            intent.putExtras(arguments)
        }
        startActivity(intent)
    }
}
