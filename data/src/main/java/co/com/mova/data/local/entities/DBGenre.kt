package co.com.mova.data.local.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by oscarg798 on 4/7/18.
 */
@Entity(tableName = "genre")
data class DBGenre(@PrimaryKey val id: Int,
                   val name: String)