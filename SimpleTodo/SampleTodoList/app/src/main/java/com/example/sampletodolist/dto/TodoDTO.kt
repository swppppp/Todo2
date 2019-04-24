package com.example.sampletodolist.dto

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class TodoDTO : RealmObject() {
    @PrimaryKey
    open var todoID:Long = 0
    open var userID:String? = null
    open var content:String? = null
    open var isTodo:Boolean = false
}