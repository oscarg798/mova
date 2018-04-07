package co.com.mova.splash

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import co.com.mova.BaseApplication
import co.com.mova.R

class SplashActivity : AppCompatActivity(), ISplashActivityView {

    private lateinit var mPresenter: ISplashActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initComponents()
    }


    override fun initComponents() {
        val presenter = SplashActivityPresenter()
        (application as BaseApplication).appComponent.inject(presenter)
        mPresenter = presenter
        lifecycle.addObserver(mPresenter)
        mPresenter.bind(this)
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
