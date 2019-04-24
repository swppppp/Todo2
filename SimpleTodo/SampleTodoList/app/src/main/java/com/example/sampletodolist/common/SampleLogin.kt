package com.example.sampletodolist.common

import android.app.Application
import io.realm.Realm

class SampleTodoList : Application(){
    override fun onCreate() {
        super.onCreate()

        Realm.init(this)
    }
}