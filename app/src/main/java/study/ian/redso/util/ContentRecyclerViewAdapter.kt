package study.ian.redso.util

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions
import com.bumptech.glide.request.RequestOptions
import study.ian.redso.R
import study.ian.redso.service.model.Banner
import study.ian.redso.service.model.Employee
import study.ian.redso.service.model.Result

private const val IMG_LOAD_DURATION = 300

class ContentRecyclerViewAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val tag = "ContentRecyclerViewAdapter"

    private var resultList = ArrayList<Result>()

    fun addResultData(_resultList: List<Result>) {
        val addedPosition = resultList.size
        resultList.addAll(_resultList)
        notifyItemInserted(addedPosition)
    }

    fun clearData() {
        resultList.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE.BANNER.ordinal) {
            BannerHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.holder_banner,
                    parent,
                    false
                )
            )
        } else {
            EmployeeHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.holder_employee,
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemCount() = resultList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is EmployeeHolder) {
            holder.bindViewHolder(resultList[position] as Employee)
        } else if (holder is BannerHolder) {
            holder.bindViewHolder(resultList[position] as Banner)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (resultList[position].type == TYPE.EMPLOYEE.type) {
            TYPE.EMPLOYEE.ordinal
        } else {
            TYPE.BANNER.ordinal
        }
    }

    class EmployeeHolder(_view: View) : RecyclerView.ViewHolder(_view) {
        private var holder: ConstraintLayout = _view.findViewById(R.id.holder_layout)
        private var avatarView: ImageView = _view.findViewById(R.id.avatarView)
        private var nameText: TextView = _view.findViewById(R.id.nameText)
        private var positionText: TextView = _view.findViewById(R.id.positionText)
        private var expertiseText: TextView = _view.findViewById(R.id.expertiseText)

        fun bindViewHolder(employee: Employee) {
            holder.animation = AnimationUtils.loadAnimation(holder.context, R.anim.item_up)

            val options = RequestOptions()
                .circleCrop()
            Glide.with(avatarView)
                .load(employee.avatar)
                .apply(options)
                .into(avatarView)
            nameText.text = employee.name
            positionText.text = employee.position
            expertiseText.text = employee.expertise.toString()
        }
    }

    class BannerHolder(_view: View) : RecyclerView.ViewHolder(_view) {
        private var holder: ConstraintLayout = _view.findViewById(R.id.holder_layout)
        private var bannerView: ImageView = _view.findViewById(R.id.bannerView)

        fun bindViewHolder(banner: Banner) {
            holder.animation = AnimationUtils.loadAnimation(holder.context, R.anim.item_up)

            Glide.with(bannerView)
                .asBitmap()
                .load(banner.url)
                .override(bannerView.width, bannerView.width)
                .transition(BitmapTransitionOptions().crossFade(IMG_LOAD_DURATION))
                .into(bannerView)
        }
    }
}