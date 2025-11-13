package com.guilherme_delecrode.smartcash.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import kotlin.math.roundToInt


@OptIn(ExperimentalTextApi::class)
@Composable
fun MonthGrafic(
    incomeValues: List<Float>,
    expenseValues: List<Float>,
    labels: List<String>,
    incomeColor: Color,
    expenseColor: Color,
    modifier: Modifier = Modifier
) {
    require(incomeValues.size == expenseValues.size && incomeValues.size == labels.size)

    val max = (incomeValues + expenseValues).maxOrNull()?.coerceAtLeast(1f) ?: 1f
    val animIncome = remember(incomeValues) { incomeValues.map { Animatable(0f) } }
    val animExpense = remember(expenseValues) { expenseValues.map { Animatable(0f) } }
    val textMeasurer = rememberTextMeasurer()
    val labelTextStyle = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
    val paddingLeft = 50.dp

    LaunchedEffect(Unit) {
        animIncome.forEachIndexed { i, anim -> launch { anim.animateTo(incomeValues[i] / max, tween(800)) } }
        animExpense.forEachIndexed { i, anim -> launch { anim.animateTo(expenseValues[i] / max, tween(800)) } }
    }

    Column(modifier = modifier) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)) {
            val density = LocalDensity.current
            val paddingLeftPx = with(density) { paddingLeft.toPx() }

            Canvas(modifier = Modifier.fillMaxSize()) {
                val paddingRightPx = 16.dp.toPx()
                val topPaddingPx = 20.dp.toPx()
                val bottomPaddingPx = 40.dp.toPx()

                val widthChart = size.width - paddingLeftPx - paddingRightPx
                val heightChart = size.height - topPaddingPx - bottomPaddingPx

                val barCount = incomeValues.size
                val spaceBetweenGroups = (widthChart * 0.2f) / (barCount - 1).coerceAtLeast(1)
                val groupWidth = (widthChart * 0.8f) / barCount
                val singleBarWidth = groupWidth / 2.2f
                val spaceBetweenBars = (groupWidth - 2 * singleBarWidth) / 1.5f

                // Linhas horizontais (grade)
                val gridLines = 4
                for (i in 0..gridLines) {
                    val y = topPaddingPx + i * (heightChart / gridLines)
                    drawLine(
                        color = Color.LightGray.copy(alpha = 0.4f),
                        start = Offset(paddingLeftPx, y),
                        end = Offset(size.width - paddingRightPx, y)
                    )

                    val labelValue = max * (1 - i.toFloat() / gridLines)
                    val labelText = "R$${(labelValue / 1000).roundToInt()}k"
                    val measuredText = textMeasurer.measure(text = labelText, style = labelTextStyle)
                    drawText(
                        textMeasurer = textMeasurer,
                        text = labelText,
                        topLeft = Offset(paddingLeftPx - measuredText.size.width - 8.dp.toPx(), y - measuredText.size.height / 2),
                        style = labelTextStyle
                    )
                }

                // Desenha barras e labels
                incomeValues.forEachIndexed { i, _ ->
                    val animInc = animIncome[i].value
                    val animExp = animExpense[i].value

                    val groupStart = paddingLeftPx + (groupWidth + spaceBetweenGroups) * i
                    val incomeLeft = groupStart
                    val expenseLeft = groupStart + singleBarWidth + spaceBetweenBars

                    val incomeTop = topPaddingPx + (1f - animInc) * heightChart
                    val expenseTop = topPaddingPx + (1f - animExp) * heightChart
                    val bottom = topPaddingPx + heightChart

                    drawRoundRect(color = incomeColor, topLeft = Offset(incomeLeft, incomeTop), size = Size(singleBarWidth, bottom - incomeTop), cornerRadius = CornerRadius(8f, 8f))
                    drawRoundRect(color = expenseColor, topLeft = Offset(expenseLeft, expenseTop), size = Size(singleBarWidth, bottom - expenseTop), cornerRadius = CornerRadius(8f, 8f))

                    // Label dos meses abaixo das barras
                    val label = labels[i]
                    val measuredLabel = textMeasurer.measure(text = label, style = labelTextStyle)
                    val labelX = groupStart + groupWidth / 2 - measuredLabel.size.width / 2
                    val labelY = bottom + 12.dp.toPx()
                    drawText(
                        textMeasurer = textMeasurer,
                        text = label,
                        topLeft = Offset(labelX, labelY),
                        style = labelTextStyle
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalTextApi::class)
@Composable
fun YearGrafic(
    incomeValues: List<Float>,
    expenseValues: List<Float>,
    labels: List<String>,
    incomeColor: Color,
    expenseColor: Color,
    modifier: Modifier = Modifier
) {
    val max = (incomeValues + expenseValues).maxOrNull()?.coerceAtLeast(1f) ?: 1f
    val animIncome = remember(incomeValues) { incomeValues.map { Animatable(0f) } }
    val animExpense = remember(expenseValues) { expenseValues.map { Animatable(0f) } }
    val textMeasurer = rememberTextMeasurer()
    val labelTextStyle = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)

    LaunchedEffect(Unit) {
        animIncome.forEachIndexed { i, anim -> launch { anim.animateTo(incomeValues[i] / max, tween(800)) } }
        animExpense.forEachIndexed { i, anim -> launch { anim.animateTo(expenseValues[i] / max, tween(800)) } }
    }

    Box(
        modifier = modifier
            .horizontalScroll(rememberScrollState())
            .height(260.dp)
    ) {
        val chartWidth = 1400.dp

        Canvas(modifier = Modifier.width(chartWidth).fillMaxHeight()) {
            val paddingLeftPx = 60.dp.toPx()
            val paddingRightPx = 16.dp.toPx()
            val topPaddingPx = 20.dp.toPx()
            val bottomPaddingPx = 40.dp.toPx()

            val widthChart = size.width - paddingLeftPx - paddingRightPx
            val heightChart = size.height - topPaddingPx - bottomPaddingPx

            val barCount = incomeValues.size
            val spaceBetweenGroups = (widthChart * 0.2f) / (barCount - 1)
            val groupWidth = (widthChart * 0.8f) / barCount
            val singleBarWidth = groupWidth / 2.2f
            val spaceBetweenBars = (groupWidth - 2 * singleBarWidth) / 1.5f

            val gridLines = 4
            for (i in 0..gridLines) {
                val y = topPaddingPx + i * (heightChart / gridLines)
                drawLine(color = Color.LightGray.copy(alpha = 0.4f), start = Offset(paddingLeftPx, y), end = Offset(size.width - paddingRightPx, y))

                val labelValue = max * (1 - i.toFloat() / gridLines)
                val labelText = "R$${(labelValue / 1000).roundToInt()}k"
                val measuredText = textMeasurer.measure(text = labelText, style = labelTextStyle)
                drawText(
                    textMeasurer = textMeasurer,
                    text = labelText,
                    topLeft = Offset(paddingLeftPx - measuredText.size.width - 8.dp.toPx(), y - measuredText.size.height / 2),
                    style = labelTextStyle
                )
            }

            incomeValues.forEachIndexed { i, _ ->
                val animInc = animIncome[i].value
                val animExp = animExpense[i].value

                val groupStart = paddingLeftPx + (groupWidth + spaceBetweenGroups) * i
                val incomeLeft = groupStart
                val expenseLeft = groupStart + singleBarWidth + spaceBetweenBars

                val incomeTop = topPaddingPx + (1f - animInc) * heightChart
                val expenseTop = topPaddingPx + (1f - animExp) * heightChart
                val bottom = topPaddingPx + heightChart

                drawRoundRect(color = incomeColor, topLeft = Offset(incomeLeft, incomeTop), size = Size(singleBarWidth, bottom - incomeTop), cornerRadius = CornerRadius(6f, 6f))
                drawRoundRect(color = expenseColor, topLeft = Offset(expenseLeft, expenseTop), size = Size(singleBarWidth, bottom - expenseTop), cornerRadius = CornerRadius(6f, 6f))

                val label = labels[i]
                val measuredLabel = textMeasurer.measure(text = label, style = labelTextStyle)
                val labelX = groupStart + groupWidth / 2 - measuredLabel.size.width / 2
                val labelY = bottom + 12.dp.toPx()
                drawText(textMeasurer = textMeasurer, text = label, topLeft = Offset(labelX, labelY), style = labelTextStyle)
            }
        }
    }
}