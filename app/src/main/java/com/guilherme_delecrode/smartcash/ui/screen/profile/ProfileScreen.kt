package com.guilherme_delecrode.smartcash.ui.screen.profile

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.guilherme_delecrode.smartcash.R
import com.guilherme_delecrode.smartcash.ui.components.EmailTextField
import com.guilherme_delecrode.smartcash.ui.components.PrimaryButton
import com.guilherme_delecrode.smartcash.ui.theme.PrimaryButtonColor
import com.guilherme_delecrode.smartcash.ui.theme.PrimaryFieldColor
import com.guilherme_delecrode.smartcash.utils.MaskVisualTransformation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {
    var photoUri by remember { mutableStateOf<Uri?>(null) }
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Editar Perfil", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            ProfilePictureEditor(
                photoUri = photoUri,
                onEditClick = { /* */ }
            )
            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Nome Completo", modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(PrimaryFieldColor, RoundedCornerShape(8.dp)),
                placeholder = { Text("Nome Completo") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        tint = PrimaryButtonColor
                    )
                },
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

            Text(
                text = "E-mail", modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )
            EmailTextField(
                value = email,
                onValueChange = { email = it },
                imeAction = ImeAction.Next
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Telefone", modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )
            OutlinedTextField(
                value = phone,
                onValueChange = {
                    if (it.length <= 11) {
                        phone = it.filter { char -> char.isDigit() }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(PrimaryFieldColor, RoundedCornerShape(8.dp)),
                placeholder = { Text("(99) 99999-9999") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Phone,
                        contentDescription = null,
                        tint = PrimaryButtonColor
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Done
                ),
                visualTransformation = MaskVisualTransformation("(##) #####-####"),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    disabledContainerColor = PrimaryFieldColor,
                    focusedIndicatorColor = PrimaryButtonColor,
                    unfocusedIndicatorColor = PrimaryFieldColor,
                    errorIndicatorColor = MaterialTheme.colorScheme.error
                )
            )

            Spacer(modifier = Modifier.weight(1f))
            PrimaryButton(
                text = "SALVAR",
                onClick = { /* */ },
                modifier = Modifier.padding(vertical = 32.dp)
            )
        }
    }
}

@Composable
fun ProfilePictureEditor(
    modifier: Modifier = Modifier,
    photoUri: Uri?,
    onEditClick: () -> Unit
) {
    Box(
        modifier = modifier.size(100.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            shape = CircleShape,
            modifier = Modifier.fillMaxSize(),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Foto do usuário",
                contentScale = ContentScale.Crop,

                )
        }

        Box(
            modifier = Modifier
                .size(30.dp)
                .clip(CircleShape)
                .align(Alignment.BottomEnd)
                .clickable(onClick = onEditClick)
                .background(PrimaryButtonColor.copy(alpha = 0.5f))
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_camera_24),
                contentDescription = "Alterar foto",
                modifier = Modifier.align(Alignment.Center),

                )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(rememberNavController())
}
