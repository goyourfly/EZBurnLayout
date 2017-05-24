package com.goyourfly.ezburnlayout

import android.graphics.Canvas
import android.graphics.Point
import android.graphics.PointF
import android.os.SystemClock
import android.util.Log
import java.text.FieldPosition

/**
 * Created by gaoyufei on 2017/5/24.
 */

open class Particle{
    /**
     * 是否活着
     */
    var alive = true
    /**
     * 生命周期 秒
     */
    var life:Float = 0F
    /**
     * 开始延迟 秒
     */
    var startDelay:Float = 0F
    /**
     * 初始速度，两个方向
     */
    var speed = PointF()
    /**
     * 加速度两个方向
     */
    var acceleration = PointF()

    /**
     * 初始尺寸
     */
    var startSize:Float = 0F

    /**
     * 尺寸变化加速度
     */
    var sizeAcceleration:Float = 0F

    /**
     * 初始位置
     */
    var position = PointF()

    /**
     * 当前位置
     */
    var currentPosition = PointF()

    /**
     * 当前尺寸
     */
    var currentSize:Float = 0F

    val startTime = System.currentTimeMillis()/1000.toDouble();

    var latestTime = startTime
    init {

    }

    private fun calculate():Boolean{
        val useTime = getCostTime();
        "UseTime:$useTime".log()
        if(useTime < 0)
            return false

        if(useTime > life){
            alive = false
            return false
        }

        currentPosition.x = position.x + (speed.x * useTime + acceleration.x * useTime * useTime / 2).toInt()
        currentPosition.y = position.y + (speed.y * useTime + acceleration.y * useTime * useTime / 2).toInt()
        currentSize = (startSize + sizeAcceleration * useTime).toFloat()

        "Time:${System.currentTimeMillis()/1000.toDouble() - latestTime},CurrentPosition:$currentPosition".log()
        latestTime = System.currentTimeMillis()/1000.toDouble();
        return true
    }

    fun onFrame(canvas:Canvas?){
        if(calculate()) onDraw(canvas,currentPosition,currentSize)
    }

    fun getCostTime():Double = System.currentTimeMillis()/1000.toDouble() - startTime - startDelay

    /**
     * 子类应该重写这个方法
     */
    open fun onDraw(canvas:Canvas?,position: PointF,size:Float){

    }

    fun String.log(){
        Log.d("Particle",this)
    }
}
