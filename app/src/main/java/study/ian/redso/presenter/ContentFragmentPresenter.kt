package study.ian.redso.presenter

import android.util.Log
import io.reactivex.disposables.CompositeDisposable
import study.ian.redso.contractor.ContentFragmentContractor
import study.ian.redso.model.ContentFragmentModel
import study.ian.redso.service.model.Result
import study.ian.redso.util.ObserverHelper

class ContentFragmentPresenter(_view: ContentFragmentContractor.View) :
    ContentFragmentContractor.Presenter {

    private val tag = "ContentFragmentPresenter"

    private val model = ContentFragmentModel()
    private val compositeDisposable = CompositeDisposable()
    private val view = _view
    private var isLoading = false
    private var page = 0

    override fun getCatalog(team: String, clearData: Boolean) {
        isLoading = true

        if (clearData) {
            page = 0
        }

        compositeDisposable.add(
            model.getCatalog(team, page++)
                .compose(ObserverHelper.applyHelper())
                .map { response -> response.results }
                .subscribe(
                    { resultList -> processResponse(resultList, team, clearData) },
                    { error -> Log.d(tag, "getCatalog error: $error") },
                    { isLoading = false }
                )
        )
    }

    private fun processResponse(resultList: List<Result>, team: String, clearData: Boolean) {
        if (clearData) {
            view.clearData()
        }

        if (resultList.isEmpty() && page != 0) {
            page = 0
            getCatalog(team, clearData)
        } else {
            view.setData(resultList)
        }
    }

    override fun clearDisposable() {
        if (compositeDisposable.size() != 0) {
            compositeDisposable.clear()
        }
    }

    override fun disposeDisposable() {
        if (compositeDisposable.size() != 0) {
            compositeDisposable.dispose()
        }
    }

    override fun recyclerViewScrolled(lastItem: Int, totalItem: Int, team: String) {
        val loadMoreThreshold = 20

        if (!isLoading && (lastItem + loadMoreThreshold) >= totalItem) {
            getCatalog(team, false)
        }
    }
}