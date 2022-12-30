package com.tekzee.amiggos.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "balance")
class Balance(

    val availableBalance: Double =0.00


)