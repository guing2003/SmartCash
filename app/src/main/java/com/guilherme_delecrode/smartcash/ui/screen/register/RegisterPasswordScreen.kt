package com.guilherme_delecrode.smartcash.ui.screen.register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.guilherme_delecrode.smartcash.navigation.AppDestinations
import com.guilherme_delecrode.smartcash.ui.components.PasswordTextField
import com.guilherme_delecrode.smartcash.ui.components.PrimaryButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterPasswordScreen(navController: NavController) {

    val scrollState = rememberScrollState()
    val password = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }

    var passwordVisible by remember { mutableStateOf(false) }




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
            Text(
                text = "Nova Senha",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                fontSize = 14.sp
            )

            PasswordTextField(
                value = password.value,
                onValueChange = { password.value = it },
                imeAction = ImeAction.Done,
                isPasswordVisible = passwordVisible,
                onVisibilityChange = { passwordVisible = !passwordVisible },
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Confirmar Senha",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                fontSize = 14.sp
            )

            PasswordTextField(
                value = confirmPassword.value,
                onValueChange = { confirmPassword.value = it },
                imeAction = ImeAction.Done,
                isPasswordVisible = passwordVisible,
                onVisibilityChange = { passwordVisible = !passwordVisible },
            )

            Spacer(modifier = Modifier.height(16.dp))

            PrimaryButton(
                "Cadastrar",
                onClick = {navController.navigate(AppDestinations.Login.route)},
                modifier = Modifier.fillMaxWidth(),
                enabled = password.value.isNotEmpty() && confirmPassword.value.isNotEmpty() && password.value == confirmPassword.value
            )


        }
    }
}

@Preview
@Composable
fun RegisterPasswordScreenPreview() {
    RegisterPasswordScreen(navController = NavController(LocalContext.current))
}
