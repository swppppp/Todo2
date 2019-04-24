package com.example.sampletodolist.module

import android.util.Log
import io.realm.Realm
import io.realm.RealmConfiguration

open class RealmManager(val name: String){

    // initialize Realm
    var realm: Realm by lazy{
        var config = RealmConfiguration.Builder().name(name).build()
        Realm.getInstance(config)
    }
}