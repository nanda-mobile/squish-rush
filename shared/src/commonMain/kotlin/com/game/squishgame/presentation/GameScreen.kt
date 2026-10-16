package com.game.squishgame.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.IntOffset


@Composable
fun GameScreen(viewModel: GameViewModel) {

    val state = viewModel.uiState.collectAsState().value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            Text(
                "Score: ${state.score}"
            )

            Text(
                "Lives: ${state.lives}"
            )

            Text(
                "Time: ${state.timeLeft}"
            )
        }

        state.enemies.forEach { enemy ->

            Box(
                modifier = Modifier
                    .offset {
                        IntOffset(
                            enemy.x.toInt(),
                            enemy.y.toInt()
                        )
                    }
                    .size(70.dp)
                    .background(
                        Color.Red,
                        CircleShape
                    )
                    .clickable {
                        viewModel.squish(enemy.id)
                    }
            )
        }

        if (state.gameOver) {

            Card(
                modifier = Modifier
                    .align(Alignment.Center)
            ) {

                Column(
                    modifier = Modifier
                        .padding(20.dp)
                ) {

                    Text(
                        "GAME OVER"
                    )

                    Text(
                        "Score : ${state.score}"
                    )

                    Spacer(
                        Modifier.height(16.dp)
                    )

                    Button(
                        onClick = {
                            viewModel.restart()
                        }
                    ) {
                        Text("Play Again")
                    }
                }
            }
        }
    }
}