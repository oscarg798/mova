package co.com.mova.data.network.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by oscarg798 on 4/23/18.
 */
class APIMovieCast(val id: Int,
                   val name: String,
                   @SerializedName("profile_path") val profilePath: String?,
                   val character: String) {

}