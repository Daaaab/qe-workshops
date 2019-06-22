package com.futureprocessing.qe.workshops.database

import com.futureprocessing.qe.workshops.database.model.UserEntity
import com.futureprocessing.qe.workshops.model.User
import java.lang.Exception

fun UserEntity.toAppModel(): User{
    if(login != null && password != null){
        return User(login, password, id)
    }else {
        throw Exception("Database error: $login - $password");
    }
}

fun User.toDbModel() = UserEntity(id ?: 0, login, password)
