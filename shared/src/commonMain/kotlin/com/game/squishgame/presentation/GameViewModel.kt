package com.game.squishgame.presentation


import com.game.squishgame.domain.GameEngine
import com.game.squishgame.domain.GameState
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.StateFlow

class GameViewModel {

    private val engine = GameEngine()

    val uiState: StateFlow<GameState> = engine.state

    private val scope =
        CoroutineScope(
            Dispatchers.Default +
                    SupervisorJob()
        )

    init {
        startGameLoop()
    }

    private fun startGameLoop() {

        scope.launch {

            while (true) {

                if (!uiState.value.gameOver) {

                    engine.spawnEnemy()

                    uiState.value.enemies.forEach { enemy ->

                        launch {

                            delay(2000)

                            engine.removeEnemy(enemy.id)
                        }
                    }

                    delay(1000)
                }
            }
        }

        scope.launch {

            while (true) {

                if (!uiState.value.gameOver) {
                    delay(1000)
                    engine.decreaseTime()
                }
            }
        }
    }

    fun squish(id: Long) {
        engine.hitEnemy(id)
    }

    fun restart() {
        engine.reset()
    }
}