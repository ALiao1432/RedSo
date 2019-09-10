package study.ian.redso.view

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import io.reactivex.disposables.Disposable
import study.ian.redso.R
import study.ian.redso.contractor.MainActivityContractor
import study.ian.redso.presenter.MainActivityPresenter
import study.ian.redso.service.ServiceBuilder
import study.ian.redso.util.ObserverHelper

class MainActivity : AppCompatActivity(), MainActivityContractor.View {
    private val tag = "MainActivity"

    private var disposable: Disposable? = null
    private var presenter: MainActivityPresenter? = null
    private lateinit var tabLayout: TabLayout
    private var teams = arrayOf("Rangers", "Elastic", "Dynamo")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ServiceBuilder.watchNetworkState(this)

        findViews()
        presenter = MainActivityPresenter(this)
        presenter?.initViews()
    }

    private fun findViews() {
        tabLayout = findViewById(R.id.tabLayout)
    }

    private fun getData() {
        Log.d(tag, "start getData")

        disposable = ServiceBuilder.create()
            .getCatalog("elastic", 0)
            .compose(ObserverHelper.applyHelper())
            .subscribe(
                { response -> Log.d(tag, "response: ${response.results.size}") },
                { error -> Log.e(tag, "error: $error") },
                { Log.d(tag, "completed request") }
            )
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }

    override fun initViews() {
        tabLayout.setBackgroundColor(getColor(R.color.colorPrimary))
        tabLayout.setTabTextColors(Color.GRAY, Color.WHITE)
        teams.forEach { team -> tabLayout.addTab(getNewTab(team)) }
    }

    private fun getNewTab(tabName: String): TabLayout.Tab {
        return tabLayout.newTab()
            .setText(tabName)

    }
}
