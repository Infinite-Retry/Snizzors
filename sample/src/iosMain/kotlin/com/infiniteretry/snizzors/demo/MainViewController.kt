@file:OptIn(ExperimentalForeignApi::class)

package com.infiniteretry.snizzors.demo

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.UIKitView
import androidx.compose.ui.window.ComposeUIViewController
import com.infiniteretry.snizzors.SnizzorsUIView
import kotlinx.cinterop.ExperimentalForeignApi

fun MainViewController() = ComposeUIViewController {
  var buttonState by remember { mutableStateOf(ButtonState.Snizzors) }

  Surface(
    modifier = Modifier.fillMaxSize(),
    color = MaterialTheme.colorScheme.background,
  ) {
    AnimatedGradientBackground(
      modifier = Modifier
        .fillMaxSize()
        .background(
          brush = Brush.radialGradient(
            colors = listOf(
              Color(0xFF6A11CB),
              Color(0xFF2575FC),
            ),
          ),
        ),
    ) {
      SingleChoiceSegmentedButtonRow(
        modifier = Modifier
          .padding(16.dp)
          .align(Alignment.TopCenter),
      ) {
        ButtonState.entries.forEachIndexed { index, state ->
          SegmentedButton(
            shape = SegmentedButtonDefaults.itemShape(index = index, count = ButtonState.entries.size),
            onClick = { buttonState = ButtonState.entries[index] },
            selected = ButtonState.entries[index] == buttonState,
            colors = SegmentedButtonDefaults.colors(
              activeContainerColor = Color.White.copy(alpha = 0.8f),
              activeContentColor = Color(0xFF2575FC),
              inactiveContainerColor = Color.Transparent,
              inactiveContentColor = Color.White,
              inactiveBorderColor = Color.White.copy(alpha = 0.5f),
            ),
          ) {
            Text(state.name)
          }
        }
      }

      val uiViewModifier = Modifier
        .width(280.dp)
        .height(300.dp)
        .align(Alignment.Center)

      when (buttonState) {
        ButtonState.Builtin -> {
          UIKitView(
            factory = { DemoView() },
            modifier = uiViewModifier,
          )
        }

        ButtonState.Snizzors -> {
          SnizzorsUIView(
            factory = { DemoView() },
            modifier = uiViewModifier,
          )
        }
      }
    }
  }
}

@Composable
fun AnimatedGradientBackground(
  modifier: Modifier = Modifier,
  content: @Composable BoxScope.() -> Unit,
) {
  val infiniteTransition = rememberInfiniteTransition()

  val colorSet1 = listOf(Color(0xFFF44336), Color(0xFFFFEB3B), Color(0xFF4CAF50))
  val colorSet2 = listOf(Color(0xFFFFEB3B), Color(0xFF4CAF50), Color(0xFF2196F3))

  val animatedColors1 = infiniteTransition.animateColor(
    initialValue = colorSet1[0],
    targetValue = colorSet2[0],
    animationSpec = infiniteRepeatable(
      animation = tween(durationMillis = 10000, easing = LinearEasing),
      repeatMode = RepeatMode.Reverse,
    ),
  )

  val animatedColors2 = infiniteTransition.animateColor(
    initialValue = colorSet1[1],
    targetValue = colorSet2[1],
    animationSpec = infiniteRepeatable(
      animation = tween(durationMillis = 10000, easing = LinearEasing),
      repeatMode = RepeatMode.Reverse,
    ),
  )

  val animatedColors3 = infiniteTransition.animateColor(
    initialValue = colorSet1[2],
    targetValue = colorSet2[2],
    animationSpec = infiniteRepeatable(
      animation = tween(durationMillis = 10000, easing = LinearEasing),
      repeatMode = RepeatMode.Reverse,
    ),
  )

  val gradientColors = listOf(animatedColors1.value, animatedColors2.value, animatedColors3.value)

  Box(
    modifier = modifier
      .background(
        brush = Brush.linearGradient(
          colors = gradientColors,
          start = androidx.compose.ui.geometry.Offset(0f, 0f),
          end = androidx.compose.ui.geometry.Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY),
          tileMode = TileMode.Clamp,
        )
      )
  ) {
    content()
  }
}

private enum class ButtonState {
  Snizzors,
  Builtin,
}
