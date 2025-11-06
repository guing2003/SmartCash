package com.guilherme_delecrode.smartcash.ui.screen.register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.guilherme_delecrode.smartcash.ui.components.DefaultTextField
import com.guilherme_delecrode.smartcash.ui.components.EmailTextField
import com.guilherme_delecrode.smartcash.utils.MaskVisualTransformation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterInfoScreen(navController: NavController) {

    val email = remember { mutableStateOf("") }
    val name = remember { mutableStateOf("") }
    val birthdate = remember { mutableStateOf("") }
    val phone = remember { mutableStateOf("") }
    val cpf = remember { mutableStateOf("") }
    val scrollState = rememberScrollState()


    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Cadastro",
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Preencha todos os campos corretamente!", fontSize = 16.sp)

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "E-mail",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                fontSize = 14.sp
            )

            EmailTextField(
                value = email.value,
                onValueChange = { email.value = it },
                imeAction = ImeAction.Next
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Nome Completo",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                fontSize = 14.sp
            )
            DefaultTextField(
                value = name.value,
                onValueChange = { name.value = it },
                label = "Nome Completo",
                icon = Icons.Default.Person
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Data de Nascimento",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                fontSize = 14.sp
            )
            DefaultTextField(
                value = birthdate.value,
                onValueChange = {
                    if (it.length <= 8) {
                        birthdate.value = it.filter { char -> char.isDigit() }
                    }
                },
                label = "DD/MM/AAAA",
                icon = Icons.Default.DateRange,
                keyboardType = KeyboardType.Number,
                visualTransformation = MaskVisualTransformation("##/##/####")
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Telefone",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                fontSize = 14.sp
            )
            DefaultTextField(
                value = phone.value,
                onValueChange = {
                    if (it.length <= 11) {
                        phone.value = it.filter { char -> char.isDigit() }
                    }
                },
                label = "(99) 99999-9999",
                icon = Icons.Default.Phone,
                keyboardType = KeyboardType.Phone,
                visualTransformation = MaskVisualTransformation("(##) #####-####")
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "CPF",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                fontSize = 14.sp
            )
            DefaultTextField(
                value = cpf.value,
                onValueChange = {
                    if (it.length <= 11) {
                        cpf.value = it.filter { char -> char.isDigit() }
                    }
                },
                label = "123.456.789-00",
                icon = Icons.Default.Person,
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done,
                visualTransformation = MaskVisualTransformation("###.###.###-##")
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterInfoScreenPreview() {
    RegisterInfoScreen(rememberNavController())
}
