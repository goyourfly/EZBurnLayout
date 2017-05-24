package com.goyourfly.ezburnlayout

import android.graphics.Canvas
import android.graphics.PointF
import java.util.*

/**
 * Created by gaoyufei on 2017/5/24.
 */

class ParticleFactory{
    val rangeOfPosition = Range<PointF>(PointF(),PointF())
    val rangeOfAcceleration = Range<PointF>(PointF(),PointF())
    val rangeOfSpeed = Range<PointF>(PointF(), PointF())
    val rangeOfStartSize = Range<Float>(0F,0F)
    val rangeOfSizeAcceleration = Range<Float>(0F,0F)
    val rangeOfLife = Range<Float>(0F,0F)
    val rangeOfStartDelay = Range<Float>(0F,0F)

    var duration = 0L
    var maxParticle = 50

    val particles = mutableListOf<Particle>()
    val waitDeleteParticles = mutableListOf<Particle>()

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
        particle.position = PointF(nextFloat(rangeOfPosition.from.x,rangeOfPosition.to.x),nextFloat(rangeOfPosition.from.y,rangeOfPosition.to.y))
        particle.speed = PointF(nextFloat(rangeOfSpeed.from.x,rangeOfSpeed.to.x),nextFloat(rangeOfSpeed.from.y,rangeOfSpeed.to.y))
        particle.acceleration = PointF(nextFloat(rangeOfAcceleration.from.x,rangeOfAcceleration.to.x),nextFloat(rangeOfAcceleration.from.y,rangeOfAcceleration.to.y))
        particle.startSize = nextFloat(rangeOfStartSize.from,rangeOfStartSize.to)
        particle.sizeAcceleration = nextFloat(rangeOfSizeAcceleration.from,rangeOfSizeAcceleration.to)
        particle.life = nextFloat(rangeOfLife.from,rangeOfLife.to)
        particle.startDelay = nextFloat(rangeOfStartDelay.from,rangeOfStartDelay.to)
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
