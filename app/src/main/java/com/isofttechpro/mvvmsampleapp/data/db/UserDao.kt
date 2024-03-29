package com.isofttechpro.mvvmsampleapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.isofttechpro.mvvmsampleapp.data.db.entities.CURRENT_USER_ID
import com.isofttechpro.mvvmsampleapp.data.db.entities.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(user: User) : Long

    @Query("SELECT * FROM user where uid = $CURRENT_USER_ID")
    fun getUser() : LiveData<User>
}