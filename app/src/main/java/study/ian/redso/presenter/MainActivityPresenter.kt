package study.ian.redso.presenter

import study.ian.redso.contractor.MainActivityContractor

class MainActivityPresenter(_view: MainActivityContractor.View) : MainActivityContractor.Presenter {

    private var view = _view
//    private var model

    fun initViews() { view.initViews() }
}