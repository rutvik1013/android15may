package com.example.realm

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class MyRealmConfiguration:Application() {
    override fun onCreate() {
        super.onCreate()

        Realm.init(this)
         var configuration=RealmConfiguration.Builder()
             .name("rutvik.db")
             .deleteRealmIfMigrationNeeded()
             .schemaVersion(1)
             .build()

        Realm.setDefaultConfiguration(configuration)
    }
}