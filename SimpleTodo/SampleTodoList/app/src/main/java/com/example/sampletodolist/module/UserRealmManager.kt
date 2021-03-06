package com.example.sampletodolist.module

import com.example.sampletodolist.dto.TodoDTO
import com.example.sampletodolist.dto.UserDTO
import io.realm.RealmConfiguration
import io.realm.RealmModel
import io.realm.RealmResults
import io.realm.kotlin.where

class UserRealmManager : RealmManager("UserDTO.realm"){
    fun <T: RealmModel, E: UserDTO>insertUser(targetDto: Class<T>, dto: E){

        realm.beginTransaction()

        //PrimaryKey 증가해서 넣어주는 것이 중요!!
        var nextNum : Long = realm.where(targetDto).count() +1
        val account = realm.createObject(targetDto, nextNum)
        if(account is UserDTO){
            account.id = dto.id
            account.password = dto.password
            account.email = dto.email
        }
        realm.commitTransaction()
    }

    override fun clear() {
        val config = RealmConfiguration.Builder().name(name).build()
        if(config != null){
            realm.beginTransaction()

            //realm.where(UserDTO::class.java).findAll().deleteAllFromRealm()
            realm.delete(UserDTO::class.java)

            realm.commitTransaction()
            realm.close()
        }
    }
}