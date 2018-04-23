package co.com.mova.detail.cast

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import co.com.mova.R
import co.com.mova.core.entities.MovieCast
import co.com.mova.data.IMAGE_URL
import com.squareup.picasso.Picasso

/**
 * Created by oscarg798 on 4/23/18.
 */
class CastRecyclerViewAdapter(private val mCast: ArrayList<MovieCast>) : RecyclerView.Adapter<CastItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastItemViewHolder {
        return CastItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cast_item, parent, false))
    }

    override fun getItemCount(): Int {
        return mCast.size
    }

    override fun onBindViewHolder(holder: CastItemViewHolder, position: Int) {
        Picasso.get().load("$IMAGE_URL${mCast[position].profilePath?:""}").into(holder.mIVCastAvatar)
        holder.mTVCastName.text = mCast[position].name
        holder.mTVCastCharacter.text = mCast[position].character

    }

    fun add(casts: List<MovieCast>) {
        mCast.addAll(casts)
        notifyDataSetChanged()

    }

    fun clear(){
        mCast.clear()
        notifyDataSetChanged()
    }
}