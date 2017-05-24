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

    val circleParticle = CircleParticle()

    fun initConfig(){
        circleParticle.position = PointF(200.0F,200F)
        circleParticle.life = 10F
        circleParticle.acceleration = PointF(40F,-200F)
        circleParticle.speed = PointF(100F,500F)
        circleParticle.startSize = 20F
        circleParticle.sizeAcceleration = 20F
    }



    override fun dispatchDraw(canvas: Canvas?) {
        super.dispatchDraw(canvas)
        circleParticle.onFrame(canvas)
        invalidate()
    }

}
