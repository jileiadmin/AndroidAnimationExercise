package com.engineer.imitate.ui.widget.custom

import android.content.Context
import android.graphics.*
import androidx.appcompat.widget.AppCompatImageView
import android.util.AttributeSet

/**
 *
 * @author: Rookie
 * @date: 2018-09-09
 * @desc
 */

class RoundImageView : AppCompatImageView {


    constructor(context: Context) : super(context) {
        init(context)
    }


    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context)
    }


    private var mRadius: Float = 0.toFloat() // dp
    private lateinit var mRoundedRectPath: Path
    private lateinit var mRectF: RectF
    private lateinit var mPaint: Paint

    private fun init(context: Context) {
        val density = context.resources.displayMetrics.density
        mRadius = 2.0f * density
        mRoundedRectPath = Path()
        mRectF = RectF()
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        this.mRectF.set(0.0f, 0.0f, width.toFloat(), height.toFloat())
        mRoundedRectPath.addRoundRect(mRectF, mRadius, mRadius, Path.Direction.CW)
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawRoundRect(mRectF, mRadius, mRadius, mPaint)
//        canvas.clipPath(mRoundedRectPath)
        super.onDraw(canvas)

    }
}