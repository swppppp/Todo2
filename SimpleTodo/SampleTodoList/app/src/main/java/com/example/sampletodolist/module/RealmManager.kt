package com.example.sampletodolist.module

import android.util.Log
import com.example.sampletodolist.common.Constants
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmModel
import io.realm.RealmResults
import io.realm.kotlin.deleteFromRealm
import io.realm.kotlin.where

open class RealmManager(val name: String){

    // initialize Realm
    val realm: Realm by lazy{
        val config = RealmConfiguration.Builder().name(name).build()
        Realm.getInstance(config)
    }

    // delete every data saved at Realm
    open fun clear() {
        val config = RealmConfiguration.Builder().name(name).build()
        if(config != null){
            realm.close()
            Realm.deleteRealm(config)
        }
    }

    // T로 받은 realModel 데이터에서 키벨류 값을 가진 데이터 찾기
    fun <T: RealmModel> find(value: String, key: String, targetDto: Class<T>): T? {
        Log.e(Constants.LOG_TEST, "realm address: "+realm.path)
        return realm.where(targetDto).equalTo(key, value).findFirst()
    }

    // T로 받은 realModel 데이터에서 모든 값을 리턴
    fun <T: RealmModel> findAll(value: String, key: String, targetDto: Class<T>): RealmResults<T>? {
        Log.e(Constants.LOG_TEST, "realm address: "+realm.path)
        if(realm.where(targetDto).equalTo(key, value).findAll().size==0){
            return null
        }
        return realm.where(targetDto).equalTo(key, value).findAll()
    }

    // 특정 데이터 remove
    fun <T:RealmModel> removeAt(position:Int, dataList: RealmResults<T>): RealmResults<T> {
        if(position >= 0 && position <dataList.size){
            realm.beginTransaction()
            dataList[position]?.deleteFromRealm()
            realm.commitTransaction()
        }
        return dataList
    }

    fun <T: RealmModel> removeAt(value: String, key: String, position:Int, targetDto: Class<T>): RealmResults<T>{
        var dataList = realm.where(targetDto).equalTo(key, value).findAll()
        if(position >= 0 && position < dataList.size){
            realm.beginTransaction()
            dataList[position]?.deleteFromRealm()
            realm.commitTransaction()
        }

        return dataList
    }
}