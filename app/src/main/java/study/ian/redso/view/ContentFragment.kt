package study.ian.redso.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import study.ian.redso.R
import study.ian.redso.contractor.ContentFragmentContractor
import study.ian.redso.presenter.ContentFragmentPresenter
import study.ian.redso.service.model.Result
import study.ian.redso.util.ContentRecyclerViewAdapter
import study.ian.redso.util.DividerDecoration

class ContentFragment : Fragment(), ContentFragmentContractor.View {

    private lateinit var presenter: ContentFragmentContractor.Presenter
    private lateinit var contentRecyclerView: RecyclerView
    private lateinit var refreshLayout: SwipeRefreshLayout
    private lateinit var contentAdapter: ContentRecyclerViewAdapter
    private lateinit var ctx: Context
    private var teamType: String? = null

    companion object {
        private const val CONTENT_TYPE = "type"

        fun newInstance(contentType: String): Fragment {
            val fragment = ContentFragment()

            val bundle = Bundle()
            bundle.putString(CONTENT_TYPE, contentType)
            fragment.arguments = bundle

            return fragment
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        ctx = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.layout_content_fragment, container, false)
        findViews(v)
        initViews()

        presenter = ContentFragmentPresenter(this)
        presenter.getCatalog(teamType!!, false)

        return v
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        teamType = arguments?.getString(CONTENT_TYPE)
    }

    private fun findViews(v: View) {
        contentRecyclerView = v.findViewById(R.id.contentRecyclerView)
        refreshLayout = v.findViewById(R.id.refreshLayout)
    }

    private fun initViews() {
        initRecyclerView()
        initRefreshLayout()
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(ctx, RecyclerView.VERTICAL, false)
        contentRecyclerView.layoutManager = layoutManager

        contentAdapter = ContentRecyclerViewAdapter()
        contentRecyclerView.adapter = contentAdapter

        val decoration = DividerDecoration(
            ctx,
            layoutManager.orientation,
            ctx.getDrawable(R.drawable.item_divider)!!
        )
        contentRecyclerView.addItemDecoration(decoration)

        contentRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()
                val totalItems = layoutManager.itemCount
                presenter.recyclerViewScrolled(lastVisibleItem, totalItems, teamType!!)
            }
        })
    }

    private fun initRefreshLayout() {
        refreshLayout.setOnRefreshListener { presenter.getCatalog(teamType!!, true) }
    }

    override fun setData(resultList: List<Result>) {
        contentAdapter.addResultData(resultList)
    }

    override fun clearData() {
        contentAdapter.clearData()
        refreshLayout.isRefreshing = false
    }

    override fun onStop() {
        presenter.clearDisposable()

        super.onStop()
    }

    override fun onDestroy() {
        presenter.disposeDisposable()

        super.onDestroy()
    }
}