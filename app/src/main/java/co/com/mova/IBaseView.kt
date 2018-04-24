package co.com.post

import android.os.Bundle
import android.view.View

/**
 * Created by oscarg798 on 4/7/18.
 */
interface IBaseView {

    fun initComponents()

    fun showProgressBar()

    fun hideProgressBar()

    fun navigate(destination: Class<*>, arguments: Bundle?, options: Pair<View, String>?)

}