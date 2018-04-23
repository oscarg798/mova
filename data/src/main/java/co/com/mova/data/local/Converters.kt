package co.com.mova.data.local

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by oscarg798 on 4/7/18.
 */
class Converters {
    @TypeConverter
    fun arrayListIntToString(array: List<Int>): String {
        return Gson().toJson(array)
    }

    @TypeConverter
    fun stringToListInt(str: String?): List<Int> {

            return Gson().fromJson(str, object : TypeToken<List<Int>>() {}.type)

    }

}