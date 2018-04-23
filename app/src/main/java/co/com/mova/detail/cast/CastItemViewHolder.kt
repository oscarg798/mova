package co.com.mova.detail.cast

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import co.com.mova.R

/**
 * Created by oscarg798 on 4/23/18.
 */
class CastItemViewHolder(mItemView:View):RecyclerView.ViewHolder(mItemView){

    val mIVCastAvatar = mItemView.findViewById<ImageView>(R.id.mIVCastAvatar)
    val mTVCastName = mItemView.findViewById<TextView>(R.id.mTVCastName)
    val mTVCastCharacter = mItemView.findViewById<TextView>(R.id.mTVCastCharacter)

}