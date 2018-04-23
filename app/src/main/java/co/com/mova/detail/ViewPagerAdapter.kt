package co.com.mova.detail

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by oscarg798 on 4/23/18.
 */
class ViewPagerAdapter(fragmentManager: FragmentManager,
                       private val mFragments: ArrayList<Fragment>,
                       private  val mTitles:List<String>) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return mFragments[position]
    }

    override fun getCount(): Int {
        return mFragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mTitles[position]
    }


}