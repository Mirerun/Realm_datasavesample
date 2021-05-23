package app.murauchi.mirerun.realm_databasesample

import io.realm.RealmObject

open class Memo (
    open var title: String = "",
    open var content: String = ""
) : RealmObject() //継承、Realmで保存できるカタチにする