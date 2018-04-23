package co.com.mova.detail.cast


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import co.com.mova.R
import co.com.mova.data.MOVIE_ID


/**
 * A simple [Fragment] subclass.
 */
class CastFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cast, container, false)
    }

    companion object {

        fun newInstance(movieID: String):CastFragment{
            val fragment = CastFragment()
            val bundle = Bundle()
            bundle.putString(MOVIE_ID,movieID)
            fragment.arguments = bundle
            return fragment

        }
    }

}// Required empty public constructor
