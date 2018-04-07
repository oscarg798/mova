package co.com.post

import android.os.Bundle

/**
 * Created by oscarg798 on 4/7/18.
 */
interface IBaseView {

    fun initComponents()

    fun showProgressBar()

    fun hideProgressBar()

    fun navigate(destination: Class<*>, arguments: Bundle?)

}