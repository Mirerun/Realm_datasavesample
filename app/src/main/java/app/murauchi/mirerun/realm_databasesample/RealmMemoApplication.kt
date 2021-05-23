package app.murauchi.mirerun.realm_databasesample

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class RealmMemoApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        Realm.init(this) //Realm初期化
        val realmConfig: RealmConfiguration = RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(realmConfig)
    }
}