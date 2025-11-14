package com.guilherme_delecrode.smartcash.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.guilherme_delecrode.smartcash.R
import com.guilherme_delecrode.smartcash.navigation.AppDestinations
import com.guilherme_delecrode.smartcash.ui.components.HomeTopBar
import com.guilherme_delecrode.smartcash.ui.components.MonthGrafic
import com.guilherme_delecrode.smartcash.ui.theme.Negative
import com.guilherme_delecrode.smartcash.ui.theme.Positive
import com.guilherme_delecrode.smartcash.ui.theme.PrimaryButtonColor

@Composable
fun HomeScreen(navController: NavController) {
    val incomeData = listOf(600f, 750f, 550f, 900f)
    val expenseData = listOf(400f, 500f, 450f, 600f)
    val labels = listOf("Sem 1", "Sem 2", "Sem 3", "Sem 4")

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(AppDestinations.NewRevenueExpense.route) },
                containerColor = PrimaryButtonColor,
                contentColor = Color.White
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Adicionar transação"
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
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
                    Text(
                        "Receitas x Despesas (Mensal)",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    ChartLegend(incomeColor = Positive, expenseColor = Negative)
                    Spacer(modifier = Modifier.height(8.dp))
                    MonthGrafic(
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
                "Histórico de transações",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .background(MaterialTheme.colorScheme.surfaceVariant, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_launcher_foreground),
                        contentDescription = "Icone da categoria",
                        Modifier.size(24.dp)
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text("Salário", style = MaterialTheme.typography.bodyLarge)
                    Text("05/07/2024", style = MaterialTheme.typography.bodySmall)
                }

                Text("R$ 600,00", color = Positive, fontWeight = FontWeight.SemiBold)
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
        Box(modifier = Modifier.size(10.dp).background(incomeColor, CircleShape))
        Spacer(modifier = Modifier.width(4.dp))
        Text("Receitas", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Spacer(modifier = Modifier.width(16.dp))
        Box(modifier = Modifier.size(10.dp).background(expenseColor, CircleShape))
        Spacer(modifier = Modifier.width(4.dp))
        Text("Despesas", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(rememberNavController())
}
