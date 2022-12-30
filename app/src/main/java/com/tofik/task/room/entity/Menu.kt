package com.tekzee.amiggos.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "notes")
class Notes(

    val note: String = "",

    val available: Int = 0


)