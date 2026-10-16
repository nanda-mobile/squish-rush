package com.game.squishgame.domain

data class GameState(
    val score: Int = 0,
    val lives: Int = 3,
    val timeLeft: Int = 60,
    val enemies: List<Enemy> = emptyList(),
    val gameOver: Boolean = false
)