package study.ian.redso.service

import android.content.Context
import io.reactivex.Observable
import io.reactivex.processors.PublishProcessor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import study.ian.networkstateutil.ConnectionType
import study.ian.networkstateutil.NetworkStateChangeListener
import study.ian.networkstateutil.NetworkStateUtil
import study.ian.redso.util.getGson

class ServiceBuilder {
    companion object {
        private const val apiBaseUrl = "https://us-central1-redso-challenge.cloudfunctions.net/"
        private val connectionTypeProcessor = PublishProcessor.create<ConnectionType>()

        fun create(): RedSoService {
            val retrofit = Retrofit.Builder()
                .baseUrl(apiBaseUrl)
                .addConverterFactory(GsonConverterFactory.create(getGson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

            return retrofit.create(RedSoService::class.java)
        }

        fun watchNetworkState(context: Context) {
            NetworkStateUtil(context, object: NetworkStateChangeListener {
                override fun onConnected(connectionType: ConnectionType?) {
                    connectionTypeProcessor.onNext(connectionType)
                }

                override fun onDisconnected() {

                }
            })
        }

        fun getConnectStateObservable(): Observable<ConnectionType> {
            return connectionTypeProcessor.toObservable()
        }
    }
}