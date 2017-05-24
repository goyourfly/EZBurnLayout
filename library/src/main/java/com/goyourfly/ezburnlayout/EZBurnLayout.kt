package com.goyourfly.ezburnlayout

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Point
import android.graphics.PointF
import android.util.AttributeSet
import android.widget.FrameLayout

/**
 * Created by gaoyufei on 2017/5/24.
 */

class EZBurnLayout : FrameLayout {
    constructor(context: Context) : super(context) {initConfig()}

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {initConfig()}

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {initConfig()}

    val particleFactory = ParticleFactory()

    fun initConfig(){


        particleFactory.rangeOfLife.from = 0F
        particleFactory.rangeOfLife.to = 1.5F
        particleFactory.rangeOfStartDelay.from = 0F
        particleFactory.rangeOfStartDelay.to = 1.5F
        particleFactory.rangeOfAcceleration.from = PointF(50F,10F)
        particleFactory.rangeOfAcceleration.to = PointF(100F,100F)
        particleFactory.rangeOfPosition.from = PointF(600F,400F)
        particleFactory.rangeOfPosition.to = PointF(600F,800F)
        particleFactory.rangeOfSpeed.from = PointF(200F,-400F)
        particleFactory.rangeOfSpeed.to = PointF(400F,-600F)
        particleFactory.rangeOfStartSize.from = 10F
        particleFactory.rangeOfStartSize.to = 20F
        particleFactory.rangeOfSizeAcceleration.from = -2F
        particleFactory.rangeOfSizeAcceleration.to = -10F
        particleFactory.maxParticle = 100
    }



    override fun dispatchDraw(canvas: Canvas?) {
        super.dispatchDraw(canvas)
        particleFactory.onDraw(canvas)
        particleFactory.rangeOfPosition.from.x -= 1
        particleFactory.rangeOfPosition.to.x -= 1
        invalidate()
    }

}
