package com.futureprocessing.qe.workshops.database.model

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import com.futureprocessing.qe.workshops.model.Category
import com.futureprocessing.qe.workshops.model.Priority

@Entity(
    foreignKeys = [ForeignKey(entity = UserEntity::class, parentColumns = ["id"], childColumns = ["user_id"], onDelete = CASCADE)],
    indices = [Index("user_id")]
)
data class ItemEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "isChecked")
    val isChecked: Boolean,

    @ColumnInfo(name = "category")
    val category: Category,

    @ColumnInfo(name = "priority")
    val priority: Priority,

    @ColumnInfo(name = "user_id")
    val userId: Int
)