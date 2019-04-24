package com.example.sampletodolist.dto

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class UserDTO : RealmObject() {
    @PrimaryKey
    open var num:Long = 0
    open var id:String? = null
    open var email:String? = null
    open var password:String? = null
}