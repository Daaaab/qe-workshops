package com.futureprocessing.qe.workshops.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.futureprocessing.qe.workshops.database.model.UserEntity
import io.reactivex.Single


@Dao
interface UserDao {
    @Query("SELECT * FROM userentity WHERE login LIKE :login LIMIT 1")
    fun findByName(login: String): Single<UserEntity>

    @Insert
    fun insert(userEntity: UserEntity)

    @Delete
    fun delete(userEntity: UserEntity)
}