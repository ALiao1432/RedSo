package study.ian.redso.util

import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import study.ian.redso.service.ServiceBuilder

class ObserverHelper {
    companion object {
        private val observerHelper: ObservableTransformer<Any, Any> =
            ObservableTransformer { upstream ->
                upstream.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .retryWhen { ServiceBuilder.getConnectStateObservable() }
            }

        fun <Any> applyHelper(): ObservableTransformer<Any, Any> {
            return observerHelper as ObservableTransformer<Any, Any>
        }
    }
}