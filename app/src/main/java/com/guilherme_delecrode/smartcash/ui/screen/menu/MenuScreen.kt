package com.guilherme_delecrode.smartcash.ui.screen.menu

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.guilherme_delecrode.smartcash.R
import com.guilherme_delecrode.smartcash.navigation.AppDestinations
import com.guilherme_delecrode.smartcash.ui.theme.PrimaryButtonColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Menu",
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
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                        .clickable(onClick = {
                            navController.navigate(AppDestinations.Profile.route)
                        }),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_person_24),
                        contentDescription = "Icone Categoria",
                        modifier = Modifier.size(32.dp),
                        tint = PrimaryButtonColor
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Editar Perfil", fontSize = 18.sp)
                }
            }

            item {
                Spacer(modifier = Modifier.width(12.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_lock_24),
                        contentDescription = "Icone Categoria",
                        modifier = Modifier.size(28.dp),
                        tint = PrimaryButtonColor
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Alterar Senha", fontSize = 18.sp)
                }
            }
            item {
                Spacer(modifier = Modifier.width(12.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                        .clickable(onClick = {
                            navController.navigate(AppDestinations.History.route)
                        }),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_history_24),
                        contentDescription = "Icone Categoria",
                        modifier = Modifier.size(32.dp),
                        tint = PrimaryButtonColor

                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Histórico de Transações", fontSize = 18.sp)
                }
            }

            item {
                Spacer(modifier = Modifier.width(12.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                        .clickable(onClick = {
                            navController.navigate(AppDestinations.Faq.route)
                        }),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_faq_24),
                        contentDescription = "Icone Categoria",
                        modifier = Modifier.size(32.dp),
                        tint = PrimaryButtonColor
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Perguntas Frequentes", fontSize = 18.sp)
                }
            }

            item {
                Spacer(modifier = Modifier.width(12.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                        .clickable(onClick = {
                            navController.navigate(AppDestinations.Terms.route)
                        }),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_terms_24),
                        contentDescription = "Icone Categoria",
                        modifier = Modifier.size(32.dp),
                        tint = PrimaryButtonColor
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Termos de Uso", fontSize = 18.sp)
                }
            }

            item {
                Spacer(modifier = Modifier.width(12.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                        .clickable(onClick = {
                            navController.navigate(AppDestinations.Privacy.route)
                        }),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_privacy_24),
                        contentDescription = "Icone Categoria",
                        modifier = Modifier.size(32.dp),
                        tint = PrimaryButtonColor
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Politica de Privacidade", fontSize = 18.sp)
                }
            }

            item {
                Spacer(modifier = Modifier.width(12.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                        .clickable(onClick = {
                            navController.navigate(AppDestinations.About.route)
                        }),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_terms_24),
                        contentDescription = "Icone Categoria",
                        modifier = Modifier.size(32.dp),
                        tint = PrimaryButtonColor
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Sobre", fontSize = 18.sp)
                }
            }

//            item {
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(4.dp),
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Icon(
//                        painter = painterResource(R.drawable.ic_launcher_foreground),
//                        contentDescription = "Icone Categoria",
//                        modifier = Modifier.size(48.dp)
//                    )
//                    Spacer(modifier = Modifier.width(8.dp))
//                    Text("Categorias", fontSize = 18.sp)
//                }
//            }
            item {
                Spacer(modifier = Modifier.width(12.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                        .clickable(onClick = {
                            navController.navigate(AppDestinations.Login.route) {
                                popUpTo(AppDestinations.Menu.route) {
                                    inclusive = true
                                }
                            }
                        }),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_logout_24),
                        contentDescription = "Icone Categoria",
                        modifier = Modifier.size(32.dp),
                        tint = PrimaryButtonColor
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Sair", fontSize = 18.sp)
                }
            }
        }

    }
}


@Preview
@Composable
fun MenuScreenPreview() {
    MenuScreen(navController = rememberNavController())
}