package com.tekzee.amiggos.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface transactionDao {

    @Query("SELECT * from `transaction`" )
    fun getTransacction(): LiveData<List<Transaction>>


}