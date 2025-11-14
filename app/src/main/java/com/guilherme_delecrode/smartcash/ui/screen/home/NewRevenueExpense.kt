package com.guilherme_delecrode.smartcash.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.guilherme_delecrode.smartcash.ui.components.DatePickerField
import com.guilherme_delecrode.smartcash.ui.theme.PrimaryButtonColor
import com.guilherme_delecrode.smartcash.ui.theme.PrimaryFieldColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewRevenueExpenseScreen(navController: NavController) {

    var nameTransaction by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    var expandedType by remember { mutableStateOf(false) }
    var expandedCategory by remember { mutableStateOf(false) }
    var typeSelected by remember { mutableStateOf("") }
    var categorySelected by remember { mutableStateOf("") }

    val itens = listOf("Receita", "Despesas")

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Nova Receita/Despesa",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Voltar",
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            Column(modifier = Modifier.padding(12.dp)) {

                ExposedDropdownMenuBox(
                    expanded = expandedType,
                    onExpandedChange = { expandedType = !expandedType }
                ) {
                    OutlinedTextField(
                        value = typeSelected,
                        onValueChange = { },
                        readOnly = true,
                        label = { Text("Tipo de transação") },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedType)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor()
                    )

                    ExposedDropdownMenu(
                        expanded = expandedType,
                        onDismissRequest = { expandedType = false }
                    ) {
                        itens.forEach { selection ->
                            DropdownMenuItem(
                                text = { Text(selection) },
                                onClick = {
                                    typeSelected = selection
                                    expandedType = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text("Nome")
                OutlinedTextField(
                    value = nameTransaction,
                    onValueChange = { nameTransaction = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(PrimaryFieldColor, RoundedCornerShape(8.dp)),
                    placeholder = { Text("Nome da transação") },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        disabledContainerColor = PrimaryFieldColor,
                        focusedIndicatorColor = PrimaryButtonColor,
                        unfocusedIndicatorColor = PrimaryFieldColor,
                        errorIndicatorColor = MaterialTheme.colorScheme.error
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text("Descrição")
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(PrimaryFieldColor, RoundedCornerShape(8.dp)),
                    placeholder = { Text("Descrição") },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        disabledContainerColor = PrimaryFieldColor,
                        focusedIndicatorColor = PrimaryButtonColor,
                        unfocusedIndicatorColor = PrimaryFieldColor,
                        errorIndicatorColor = MaterialTheme.colorScheme.error
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text("Categoria")
                ExposedDropdownMenuBox(
                    expanded = expandedCategory,
                    onExpandedChange = { expandedCategory = !expandedCategory }
                ) {
                    OutlinedTextField(
                        value = categorySelected,
                        onValueChange = { },
                        readOnly = true,
                        label = { Text("Categoria") },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedCategory)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor()
                    )

                    ExposedDropdownMenu(
                        expanded = expandedCategory,
                        onDismissRequest = { expandedCategory = false }
                    ) {
                        itens.forEach { selection ->
                            DropdownMenuItem(
                                text = { Text(selection) },
                                onClick = {
                                    categorySelected = selection
                                    expandedCategory = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text("Data")
                Box(modifier = Modifier.fillMaxWidth()){
                    DatePickerField(
                        label = "Data"
                    ) { date ->
                        // aqui você recebe a data selecionada
                        println("Data escolhida: $date")
                    }
                }

            }
        }
    }
}

@Preview
@Composable
fun NewRevenueExpensePreview() {
    NewRevenueExpenseScreen(rememberNavController())
}
