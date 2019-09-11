package study.ian.redso.util

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContentRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class EmployeeHolder(_view: View) : RecyclerView.ViewHolder(_view) {
        private lateinit var avatarView: ImageView
        private lateinit var nameText: TextView
        private lateinit var positionText: TextView
        private lateinit var expertiseText: TextView
    }

    class BannerHolder(_view: View) : RecyclerView.ViewHolder(_view) {
        private lateinit var bannerView: ImageView
    }
}