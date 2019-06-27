package com.cherish.note.view

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class WrapContentGridLayoutMananger(context: Context, spanCount: Int) : GridLayoutManager(context, spanCount) {
    private val mChildPerLines: Int = 0
    private val mMeasuredDimension = IntArray(2)

    override fun onMeasure(
        recycler: RecyclerView.Recycler,
        state: RecyclerView.State,
        widthSpec: Int,
        heightSpec: Int
    ) {
        val heightMode = View.MeasureSpec.getMode(heightSpec)
        val widthSize = View.MeasureSpec.getSize(widthSpec)
        val heightSize = View.MeasureSpec.getSize(heightSpec)
        var height = 0
        var i = 0
        while (i < itemCount) {
            measureScrapChild(
                recycler, i,
                View.MeasureSpec.makeMeasureSpec(i, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(i, View.MeasureSpec.UNSPECIFIED),
                mMeasuredDimension
            )
            height += mMeasuredDimension[1]
            i += mChildPerLines
        }

        // If child view is more than screen size, there is no need to make it wrap content. We can use original onMeasure() so we can scroll view.
        if (height > heightSize) {
            when (heightMode) {
                View.MeasureSpec.EXACTLY -> height = heightSize
            }
            setMeasuredDimension(widthSize, height)
        } else {
            super.onMeasure(recycler, state, widthSpec, heightSpec)
        }
    }

    private fun measureScrapChild(
        recycler: RecyclerView.Recycler, position: Int, widthSpec: Int,
        heightSpec: Int, measuredDimension: IntArray
    ) {

        val view = recycler.getViewForPosition(position)

        // For adding Item Decor Insets to view
        super.measureChildWithMargins(view, 0, 0)
        if (view != null) {
            val p = view.layoutParams as RecyclerView.LayoutParams
            val childWidthSpec = ViewGroup.getChildMeasureSpec(
                widthSpec,
                paddingLeft + paddingRight + getDecoratedLeft(view) + getDecoratedRight(view), p.width
            )
            val childHeightSpec = ViewGroup.getChildMeasureSpec(
                heightSpec,
                paddingTop + paddingBottom + paddingBottom + getDecoratedBottom(view), p.height
            )
            view.measure(childWidthSpec, childHeightSpec)

            // Get decorated measurements
            measuredDimension[0] = getDecoratedMeasuredWidth(view) + p.leftMargin + p.rightMargin
            measuredDimension[1] = getDecoratedMeasuredHeight(view) + p.bottomMargin + p.topMargin
            recycler.recycleView(view)
        }
    }
}