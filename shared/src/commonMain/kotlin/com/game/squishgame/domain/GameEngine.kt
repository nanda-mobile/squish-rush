package com.game.squishgame.domain

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.random.Random
import kotlin.time.Clock

class GameEngine {

        private val _state = MutableStateFlow(GameState())
        val state: StateFlow<GameState> = _state

        fun spawnEnemy() {
            val enemy = Enemy(
                id = Clock.System.now().toEpochMilliseconds(),
                x = Random.nextFloat() * 300f,
                y = Random.nextFloat() * 500f
            )

            _state.value = _state.value.copy(
                enemies = _state.value.enemies + enemy
            )
        }

        fun hitEnemy(id: Long) {

            val current = _state.value

            _state.value = current.copy(
                score = current.score + 10,
                enemies = current.enemies.filterNot { it.id == id }
            )
        }

        fun removeEnemy(id: Long) {

            val current = _state.value

            val enemyExists =
                current.enemies.any { it.id == id }

            if (!enemyExists) return

            _state.value = current.copy(
                enemies = current.enemies.filterNot { it.id == id },
                lives = current.lives - 1
            )

            if (_state.value.lives <= 0) {
                gameOver()
            }
        }

        fun decreaseTime() {

            val current = _state.value

            val newTime = current.timeLeft - 1

            _state.value = current.copy(
                timeLeft = newTime
            )

            if (newTime <= 0) {
                gameOver()
            }
        }

        fun gameOver() {

            _state.value = _state.value.copy(
                gameOver = true
            )
        }

        fun reset() {
            _state.value = GameState()
        }
    }
