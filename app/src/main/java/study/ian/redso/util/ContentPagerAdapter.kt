package study.ian.redso.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import study.ian.redso.view.ContentFragment

class ContentPagerAdapter(fragmentManager: FragmentManager, private val contents: Array<String>) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val tag = "ContentPagerAdapter"

    override fun getItem(position: Int): Fragment {
        return ContentFragment.newInstance(contents[position])
    }

    override fun getCount(): Int {
        return contents.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return contents[position]
    }
}