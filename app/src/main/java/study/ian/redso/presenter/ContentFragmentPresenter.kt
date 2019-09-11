package study.ian.redso.presenter

import study.ian.redso.contractor.ContentFragmentContractor

class ContentFragmentPresenter(_view: ContentFragmentContractor.View) :
    ContentFragmentContractor.Presenter {

    private var view = _view

    fun initViews(contentType: String?) {
        view.initViews(contentType)
    }
}