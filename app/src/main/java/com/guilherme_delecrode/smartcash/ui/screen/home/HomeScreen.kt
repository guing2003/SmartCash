package com.guilherme_delecrode.smartcash.ui.screen.home

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.guilherme_delecrode.smartcash.R
import com.guilherme_delecrode.smartcash.ui.components.HomeTopBar
import com.guilherme_delecrode.smartcash.ui.theme.Negative
import com.guilherme_delecrode.smartcash.ui.theme.Positive
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun HomeScreen(navController: NavController) {
    val incomeData =
        listOf(2200f, 2500f, 2100f, 2800f, 3000f, 2600f, 2900f, 3100f, 2800f, 3200f, 3300f, 3400f)
    val expenseData =
        listOf(1500f, 1800f, 1600f, 2000f, 2100f, 1900f, 2000f, 2200f, 2100f, 2400f, 2300f, 2500f)
    val labels =
        listOf("Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul", "Ago", "Set", "Out", "Nov", "Dez")

    Column(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(18.dp))
        HomeTopBar(navController)

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                ChartLegend(incomeColor = Positive, expenseColor = Negative)
                Spacer(modifier = Modifier.height(8.dp))
                GroupedColumnChart(
                    incomeValues = incomeData,
                    expenseValues = expenseData,
                    labels = labels,
                    incomeColor = Positive,
                    expenseColor = Negative,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            "Historico de transações",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(38.dp)
                    .background(Color.LightGray, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_launcher_foreground),
                    contentDescription = "Icone da categoria",
                    Modifier.size(48.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text("Categoria")
                Text("00/00/0000")
            }

            Spacer(modifier = Modifier.weight(1f))

            Text("R$00,00")
        }
    }
}


@OptIn(ExperimentalTextApi::class)
@Composable
fun GroupedColumnChart(
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
    val labelTextStyle =
        MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
    val paddingLeft = 45.dp

    LaunchedEffect(Unit) {
        animIncome.forEachIndexed { i, anim ->
            launch {
                anim.animateTo(
                    incomeValues[i] / max,
                    tween(800)
                )
            }
        }
        animExpense.forEachIndexed { i, anim ->
            launch {
                anim.animateTo(
                    expenseValues[i] / max,
                    tween(800)
                )
            }
        }
    }

    val horizontalScroll = rememberScrollState()

    Column(modifier = modifier.padding(vertical = 12.dp)) {
        Box(
            modifier = Modifier
                .horizontalScroll(horizontalScroll)
                .height(300.dp)
                .padding(start = 8.dp)
        ) {
            val density = LocalDensity.current
            val paddingLeftPx = with(density) { paddingLeft.toPx() }

            Canvas(
                modifier = Modifier
                    .width(1400.dp)
                    .fillMaxHeight()
            ) {
                val paddingRightPx = 16.dp.toPx()
                val topPaddingPx = 20.dp.toPx()
                val bottomPaddingPx = 48.dp.toPx()

                val widthChart = size.width - paddingLeftPx - paddingRightPx
                val heightChart = size.height - topPaddingPx - bottomPaddingPx

                val barCount = incomeValues.size
                val spaceBetweenGroups = 60f
                val groupWidth = (widthChart - spaceBetweenGroups * (barCount + 1)) / barCount
                val singleBarWidth = groupWidth / 2f

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
                    val measuredText =
                        textMeasurer.measure(text = labelText, style = labelTextStyle)
                    drawText(
                        textMeasurer = textMeasurer,
                        text = labelText,
                        topLeft = Offset(
                            paddingLeftPx - measuredText.size.width - 8.dp.toPx(),
                            y - measuredText.size.height / 2
                        ),
                        style = labelTextStyle
                    )
                }

                // 🔹 Linhas verticais separando os meses
                incomeValues.forEachIndexed { i, _ ->
                    val groupStart = paddingLeftPx + (groupWidth + spaceBetweenGroups) * i
                    val separatorX = groupStart + groupWidth + spaceBetweenGroups / 2
                    drawLine(
                        color = Color.LightGray.copy(alpha = 0.2f),
                        start = Offset(separatorX, topPaddingPx),
                        end = Offset(separatorX, topPaddingPx + heightChart)
                    )
                }


                // Desenha barras e labels
                incomeValues.forEachIndexed { i, _ ->
                    val animInc = animIncome[i].value
                    val animExp = animExpense[i].value

                    val groupStart = paddingLeftPx + (groupWidth + spaceBetweenGroups) * i
                    val incomeLeft = groupStart
                    val expenseLeft = groupStart + singleBarWidth + 10f

                    val incomeTop = topPaddingPx + (1f - animInc) * heightChart
                    val expenseTop = topPaddingPx + (1f - animExp) * heightChart
                    val bottom = topPaddingPx + heightChart

                    val barHeightInc = bottom - incomeTop
                    val barHeightExp = bottom - expenseTop

                    drawRoundRect(
                        color = incomeColor,
                        topLeft = Offset(incomeLeft, incomeTop),
                        size = Size(singleBarWidth, barHeightInc),
                        cornerRadius = CornerRadius(8f, 8f)
                    )
                    drawRoundRect(
                        color = expenseColor,
                        topLeft = Offset(expenseLeft, expenseTop),
                        size = Size(singleBarWidth, barHeightExp),
                        cornerRadius = CornerRadius(8f, 8f)
                    )

                    // Label dos meses abaixo das barras
                    val label = labels[i]
                    val measuredLabel = textMeasurer.measure(text = label, style = labelTextStyle)
                    val labelX =
                        incomeLeft + (singleBarWidth + 10f + singleBarWidth) / 2 - measuredLabel.size.width / 2
                    val labelY = bottom + 10f
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

@Composable
fun ChartLegend(incomeColor: Color, expenseColor: Color, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(10.dp)
                .background(incomeColor, CircleShape)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text("Receitas", style = MaterialTheme.typography.bodySmall)
        Spacer(modifier = Modifier.width(16.dp))
        Box(
            modifier = Modifier
                .size(10.dp)
                .background(expenseColor, CircleShape)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text("Despesas", style = MaterialTheme.typography.bodySmall)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGroupedChart() {
    HomeScreen(rememberNavController())
}
