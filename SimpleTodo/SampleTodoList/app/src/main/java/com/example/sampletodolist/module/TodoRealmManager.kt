package com.example.sampletodolist.module

import com.example.sampletodolist.dto.TodoDTO
import com.example.sampletodolist.dto.UserDTO
import io.realm.RealmConfiguration
import io.realm.RealmModel
import io.realm.RealmResults
import io.realm.kotlin.where

class TodoRealmManager : RealmManager("TodoDTO.realm"){
    fun <T:RealmModel, E: TodoDTO>insertTodo(targetDTO: Class<T>, dto: E) {
        realm.beginTransaction()

        // primarykey 값 증가해서 넣어줘야함!!
        val account = realm.createObject(targetDTO, dto.todoID)
        if(account is TodoDTO){
            account.userID = dto.userID
            account.content = dto.content
            account.isTodo = dto.isTodo
        }
        realm.commitTransaction()
    }

    fun <T:TodoDTO> updateCheckUserDate(isCheck: Boolean, position: Int, dataList: RealmResults<T>): RealmResults<T> {
        if(position >=0 && position<dataList.size){
            realm.beginTransaction()
            dataList?.get(position)?.isTodo = isCheck
            realm.commitTransaction()
        }
        return dataList
    }

    override fun clear() {
        val config = RealmConfiguration.Builder().name(name).build()
        if(config != null){
            realm.beginTransaction()

            realm.where(UserDTO::class.java).findAll().deleteAllFromRealm()
            realm.delete(UserDTO::class.java)

            realm.commitTransaction()
            realm.close()
        }
    }
}