package app.murauchi.mirerun.realm_databasesample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.system.Os.read
import com.google.android.material.snackbar.Snackbar
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val realm: Realm = Realm.getDefaultInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val memo: Memo? = read()

        if (memo != null){ //データベースから取得したものを反映
            titleEditText.setText(memo.title)
            contentEditText.setText(memo.content)
        }

        saveButton.setOnClickListener {
            val title: String = titleEditText.text.toString()
            val content: String = contentEditText.text.toString()
            save(title,content)
        }
    }

    override fun onDestroy() { //画面終了時に実行
        super.onDestroy()
        realm.close()
    }

    fun read(): Memo? { //Memoがnullになる可能性もあるので？
        return realm.where(Memo::class.java).findFirst()
    }

    fun save(title: String, content:String){
        //保存する処理
        val memo: Memo? = read() //すでに保存されているメモを取得

        realm.executeTransaction { //この中でデータベースへの書き込みをする
            if (memo != null){ //メモが保存されてれば
                //メモの更新
                memo.title = title
                memo.content = content
            } else{ //メモが何もなければ
                //メモの新規作成
                val newMemo: Memo = it.createObject(Memo::class.java) //新規作成の処理
                newMemo.title = title
                newMemo.content = content
            }

            //Snackbar.make(表示するView,テキスト,長さ)
            Snackbar.make(container,"保存しました!!", Snackbar.LENGTH_SHORT).show()
        }
    }
}