package co.com.mova.detail.cast


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.com.mova.BaseApplication

import co.com.mova.R
import co.com.mova.core.entities.MovieCast
import co.com.mova.data.MOVIE_ID
import kotlinx.android.synthetic.main.fragment_cast.*


/**
 * A simple [Fragment] subclass.
 */
class CastFragment : Fragment(), ICastFragmentView {


    lateinit var mPresenter: ICastFragmentPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val presenter = CastFragmentPresenter()
        (activity?.application as? BaseApplication)?.appComponent?.inject(presenter)
        mPresenter = presenter
        lifecycle.addObserver(presenter)
        mPresenter.bind(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cast, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponents()
    }

    override fun initComponents() {
        mRVCast?.layoutManager = LinearLayoutManager(activity)
        mRVCast?.adapter = CastRecyclerViewAdapter(ArrayList())
        mRVCast?.setHasFixedSize(false)


    }

    override fun showProgressBar() {
        mRVCast?.visibility = View.GONE
        mPBCast?.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        mRVCast?.visibility = View.VISIBLE
        mPBCast?.visibility = View.GONE
    }

    override fun navigate(destination: Class<*>, arguments: Bundle?) {

    }

    override fun showCast(cast: List<MovieCast>) {
        (mRVCast?.adapter as? CastRecyclerViewAdapter)?.add(cast)
    }

    override fun clear() {
        (mRVCast?.adapter as? CastRecyclerViewAdapter)?.clear()
    }


    companion object {

        fun newInstance(movieID: Int): CastFragment {
            val fragment = CastFragment()
            val bundle = Bundle()
            bundle.putInt(MOVIE_ID, movieID)
            fragment.arguments = bundle
            return fragment

        }
    }

}// Required empty public constructor
