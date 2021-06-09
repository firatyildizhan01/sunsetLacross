package com.github.nthily.flappybird.ui

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import java.util.*
import kotlin.random.Random
import kotlin.random.nextInt


enum class GameState{
    Unstarted, Running, Over
}

class Game {

    val gameObject = GameObject()
    val bird = Bird()
    val pipe by mutableStateOf<Queue<Pipe>>(LinkedList())


    var gameState by mutableStateOf(GameState.Unstarted)

    var birdState by mutableStateOf(BirdState.Falling)

    var score by mutableStateOf(0)

    fun update(time:Long){

        if(gameState == GameState.Running){
            when(birdState){
                BirdState.Jumping -> {
                    bird.jump(gameObject.jumpDistance)
                    birdState = BirdState.Falling
                }
                BirdState.Falling -> {
                    bird.y += 5f
                }
            }

            pipe.forEachIndexed { index, pipe ->
                pipe.pipeDownX -= 2f
                pipe.pipeUpX -= 2f

                // Up layer detection
                if(pipe.pipeDownHeight.value >= gameObject.limitHeight.value / 2 + bird.y.dp.value &&
                    (-pipe.pipeDownX.dp) + pipe.width / 2 >= gameObject.limitWidth / 2 - bird.width / 2 &&
                    (-pipe.pipeDownX.dp) <= gameObject.limitWidth / 2 + bird.width / 2
                ){
                    gameState = GameState.Over
                }

                // Down layer detection
                if(pipe.pipeUpHeight.value >= gameObject.limitHeight.value / 2 - bird.y.dp.value &&
                    (-pipe.pipeUpX.dp) + pipe.width / 2 >= gameObject.limitWidth / 2 - bird.width / 2 &&
                    (-pipe.pipeUpX.dp) <= gameObject.limitWidth / 2 + bird.width / 2
                ){
                    gameState = GameState.Over
                }

                // if the bird has reached the bottom
                if(bird.y.dp - bird.height / 2  >= gameObject.limitHeight / 2) {
                    gameState = GameState.Over
                }

                // TODO: If a pipe has crossed the screen, generate a new
                if(-pipe.pipeDownX.dp - pipe.width / 2 >= gameObject.limitWidth) {
                    remove()
                }

                // if the bird has crossed a pipe
                if((-pipe.pipeDownX.dp) >= gameObject.limitWidth / 2 + bird.width && !pipe.isCounted){
                    pipe.isCounted = true
                    score += 1
                }

            }
        }
        if(gameState == GameState.Over){
            //bird.y = 0f
        }

    }

    fun restartGame(){
        gameState = GameState.Unstarted
        bird.y = 0f
        pipe.clear()
        score = 0
        addPipe()
    }

    private fun remove(){
        while(pipe.iterator().hasNext()){
            pipe.remove()
        }
        addPipe()
    }

    private fun randomHeight(): Pair<Float, Float> {

        var totalHeight = 0

        if(gameObject.limitHeight != 0.dp) totalHeight = gameObject.limitHeight.value.toInt() - (bird.height.value * 4).toInt()

        val value = Random.nextInt(0..totalHeight)
            .toFloat()

        return Pair(value, totalHeight - value)
    }


    fun addPipe(){
        val height = randomHeight()
        pipe.add(Pipe(height.first.dp, height.second.dp))
    }
}

