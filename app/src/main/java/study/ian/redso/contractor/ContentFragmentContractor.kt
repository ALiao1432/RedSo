package study.ian.redso.contractor

import io.reactivex.Observable
import study.ian.redso.service.model.Response
import study.ian.redso.service.model.Result

interface ContentFragmentContractor {
    interface View {
        fun setData(resultList: List<Result>)
        fun clearData()
    }

    interface Model {
        fun getCatalog(team: String, page: Int): Observable<Response>
    }

    interface Presenter {
        fun getCatalog(team: String, clearData: Boolean)
        fun clearDisposable()
        fun disposeDisposable()
        fun recyclerViewScrolled(lastItem: Int, totalItem: Int, team: String)
    }
}