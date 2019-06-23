package com.futureprocessing.qe.workshops.database.dao

import androidx.room.*
import com.futureprocessing.qe.workshops.database.model.ItemEntity
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface ItemDao {
    @Query("SELECT * FROM itementity WHERE user_id LIKE :userId")
    fun getItems(userId: Int): Flowable<List<ItemEntity>>

    @Query("SELECT * FROM itementity WHERE id LIKE :itemId LIMIT 1")
    fun getItem(itemId: Int): Single<ItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: ItemEntity)

    @Delete
    fun delete(item: ItemEntity)
}