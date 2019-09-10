package study.ian.redso

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import study.ian.networkstateutil.NetworkStateUtil
import study.ian.redso.service.ServiceBuilder
import study.ian.redso.util.ObserverHelper

class MainActivity : AppCompatActivity() {

    private val tag = "MainActivity"
    private var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ServiceBuilder.watchNetworkState(this)

        findViewById<TextView>(R.id.sendBtn)
            .setOnClickListener {
                getData()
                Log.d(tag, "networkState: ${NetworkStateUtil.isNetworkConnected(this)}")
            }
    }

    private fun getData() {
        Log.d(tag, "start getData")

        disposable = ServiceBuilder.create()
            .getCatalog("elastic", 0)
            .compose(ObserverHelper.applyHelper())
            .subscribe(
                { response -> Log.d(tag, "response: ${response.results.size}") },
                { error -> Log.e(tag, "error: ${error}") },
                { Log.d(tag, "completed request") }
            )
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }
}
