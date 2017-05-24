package com.goyourfly.ezburnlayout

import android.graphics.Canvas
import android.graphics.PointF
import java.util.*

/**
 * Created by gaoyufei on 2017/5/24.
 */

class ParticleFactory{
    var rangeOfPositionFrom:PointF = PointF()
    var rangeOfPositionTo:PointF = PointF()
    var rangeOfAccelerationFrom:PointF = PointF()
    var rangeOfAccelerationTo:PointF = PointF()
    var rangeOfSpeedFrom:PointF = PointF()
    var rangeOfSpeedTo:PointF = PointF()
    var rangeOfStartSizeFrom:Float = 0F
    var rangeOfStartSizeTo:Float = 0F
    var rangeOfSizeAccelerationFrom:Float = 0F
    var rangeOfSizeAccelerationTo:Float = 0F
    var rangeOfLifeFrom = 0F
    var rangeOfLifeTo = 0F

    var duration = 0L
    var maxParticle = 50

    var particles = mutableListOf<Particle>()
    var waitDeleteParticles = mutableListOf<Particle>()

    val random = Random()


    fun onDraw(canvas:Canvas?){
        for (particle in particles){
            if(particle.alive){
                particle.onFrame(canvas)
            }else{
                waitDeleteParticles.add(particle)
            }
        }
        checkAlive()
        checkCreate()
    }

    fun checkAlive(){
        for (particle in waitDeleteParticles){
            particles.remove(particle)
        }
        waitDeleteParticles.clear()
    }

    fun checkCreate(){
        if(particles.size < maxParticle){
            for (i in 0..maxParticle - particles.size - 1){
                particles.add(generateParticle())
            }
        }
    }

    fun generateParticle():Particle{
        val particle = CircleParticle()
        particle.position = PointF(nextFloat(rangeOfPositionFrom.x,rangeOfPositionTo.x),nextFloat(rangeOfPositionFrom.y,rangeOfPositionTo.y))
        particle.speed = PointF(nextFloat(rangeOfSpeedFrom.x,rangeOfSpeedTo.x),nextFloat(rangeOfSpeedFrom.y,rangeOfSpeedTo.y))
        particle.acceleration = PointF(nextFloat(rangeOfAccelerationFrom.x,rangeOfAccelerationTo.x),nextFloat(rangeOfAccelerationFrom.y,rangeOfAccelerationTo.y))
        particle.startSize = nextFloat(rangeOfStartSizeFrom,rangeOfStartSizeTo)
        particle.sizeAcceleration = nextFloat(rangeOfSizeAccelerationFrom,rangeOfSizeAccelerationTo)
        particle.life = nextFloat(rangeOfLifeFrom,rangeOfLifeTo)
        return particle
    }

    fun stop(){

    }

    fun nextFloat(from:Float,to:Float):Float{
        return random.nextFloat() * (to - from) + from
    }

    fun nextInt(from:Int,to:Int):Int{
        return random.nextInt(to - from) + from
    }
}
