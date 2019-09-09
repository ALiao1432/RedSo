package study.ian.redso

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import study.ian.redso.service.ServiceBuilder

class MainActivity : AppCompatActivity() {

    private val tag = "MainActivity"
    private var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.resultText)
            .setOnClickListener { getData() }
    }

    private fun getData() {
        disposable = ServiceBuilder.create()
            .getCatalog("elastic", 0)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> Log.d(tag, "result: ${result}") },
                { error -> Log.e(tag, "error: ${error}") }
            )
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }
}
