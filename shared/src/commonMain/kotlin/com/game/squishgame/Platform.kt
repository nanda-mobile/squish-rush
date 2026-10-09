package com.game.squishgame

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform