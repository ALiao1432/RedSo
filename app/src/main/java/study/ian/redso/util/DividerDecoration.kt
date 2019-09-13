package study.ian.redso.util

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.roundToInt

class DividerDecoration(context: Context, orientation: Int, _divider: Drawable) :
    DividerItemDecoration(context, orientation) {

    private val paint: Paint = Paint()
    private val divider = _divider
    private val bounds = Rect()
    private val marginRatio = .02f

    init {
        paint.color = Color.GRAY
        paint.strokeWidth = 2f
        paint.strokeCap = Paint.Cap.ROUND
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        if (parent.layoutManager == null) {
            return
        }

        c.save()
        var left: Int
        var right: Int

        if (parent.clipToPadding) {
            left = parent.paddingLeft
            right = parent.width - parent.paddingRight
            c.clipRect(
                left, parent.paddingTop, right,
                parent.height - parent.paddingBottom
            )
        } else {
            left = 0
            right = parent.width
        }

        left += (parent.width * marginRatio).toInt()
        right -= (parent.width * marginRatio).toInt()

        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            parent.getDecoratedBoundsWithMargins(child, bounds)
            val bottom = bounds.bottom + child.translationY.roundToInt()
            val top = bottom - divider.intrinsicHeight
            divider.setBounds(left, top, right, bottom)
            divider.draw(c)
        }
        c.restore()
    }
}