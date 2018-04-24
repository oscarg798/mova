package co.com.mova.splash

import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import co.com.mova.BaseApplication
import co.com.mova.R
import kotlinx.android.synthetic.main.activity_splash.*
import java.util.*

class SplashActivity : AppCompatActivity(), ISplashActivityView {

    private lateinit var mPresenter: ISplashActivityPresenter

    private val mMinTime = 4000

    private var mResumetime = 0.toLong()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initComponents()
    }

    override fun onResume() {
        super.onResume()
        mResumetime = Calendar.getInstance().timeInMillis
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

    override fun showMessage(message: String) {
        val dialog = AlertDialog.Builder(this)
        dialog.setMessage(message)
        dialog.setCancelable(false)
        dialog.setPositiveButton(getString(R.string.accept_label)) { d, _ ->
            d?.dismiss()
            finishAffinity()
        }
        dialog.show()
    }

    override fun navigate(destination: Class<*>, arguments: Bundle?, options: Pair<View, String>?) {
        val elapesedTime = Calendar.getInstance().timeInMillis - mResumetime



        mTVReference?.postDelayed(Runnable {
            val intent = Intent(this, destination)
            arguments?.let {
                intent.putExtras(arguments)
            }
            startActivity(intent)
        }, if (elapesedTime < mMinTime) mMinTime - elapesedTime else 0)


    }
}
