package study.ian.redso.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import study.ian.redso.R
import study.ian.redso.contractor.ContentFragmentContractor
import study.ian.redso.presenter.ContentFragmentPresenter

class ContentFragment : Fragment(), ContentFragmentContractor.View {

    private lateinit var contentRecyclerView: RecyclerView
    private var contentType: String? = null
    private var presenter: ContentFragmentPresenter? = null

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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.layout_content_fragment, container, false)

        findViews(v)
        presenter = ContentFragmentPresenter(this)
        presenter?.initViews(contentType)

        return v
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        contentType = arguments?.getString(CONTENT_TYPE)
    }

    private fun findViews(v: View) {
        contentRecyclerView = v.findViewById(R.id.contentRecyclerView)
    }

    override fun initViews(contentType: String?) {
        val layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    }
}
