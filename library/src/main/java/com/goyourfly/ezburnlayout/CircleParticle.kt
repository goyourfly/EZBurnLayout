package com.goyourfly.ezburnlayout

import android.graphics.*

/**
 * Created by gaoyufei on 2017/5/24.
 */
class CircleParticle : Particle() {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        paint.color = Color.RED
    }

    override fun onDraw(canvas: Canvas?, position: PointF, size: Float) {
        canvas?.drawCircle(position.x, position.y, size, paint)
    }
}
