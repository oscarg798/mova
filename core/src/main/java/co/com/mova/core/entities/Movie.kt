package co.com.mova.core.entities

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created by oscarg798 on 4/7/18.
 */
data class Movie(val id: Int,
                 val voteCount: Int,
                 val voteAverage: Float,
                 val title: String,
                 val popularity: Float,
                 val posterPath: String,
                 val genres: ArrayList<Genre>,
                 val overview: String,
                 val releaseDate: String,
                 var favorite: Boolean) : Parcelable {

    constructor(source: Parcel) : this(
            source.readInt(),
            source.readInt(),
            source.readFloat(),
            source.readString(),
            source.readFloat(),
            source.readString(),
            source.createTypedArrayList(Genre.CREATOR),
            source.readString(),
            source.readString(),
            1 == source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
        writeInt(voteCount)
        writeFloat(voteAverage)
        writeString(title)
        writeFloat(popularity)
        writeString(posterPath)
        writeTypedList(genres)
        writeString(overview)
        writeString(releaseDate)
        writeInt((if (favorite) 1 else 0))
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Movie> = object : Parcelable.Creator<Movie> {
            override fun createFromParcel(source: Parcel): Movie = Movie(source)
            override fun newArray(size: Int): Array<Movie?> = arrayOfNulls(size)
        }
    }
}