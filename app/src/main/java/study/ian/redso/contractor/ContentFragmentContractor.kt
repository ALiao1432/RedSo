package study.ian.redso.contractor

interface ContentFragmentContractor {

    interface Presenter {
    }

    interface View {
        fun initViews(contentType: String?)
    }

    interface Model {

    }
}