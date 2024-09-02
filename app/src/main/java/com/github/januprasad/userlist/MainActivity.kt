package com.github.januprasad.userlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.januprasad.userlist.screens.Screens
import com.github.januprasad.userlist.screens.UsersDetail
import com.github.januprasad.userlist.screens.UsersScreen
import com.github.januprasad.userlist.vm.UsersVM
import com.github.januprasad.userlist.ui.theme.UserListTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val usersVM: UsersVM by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            val scope = rememberCoroutineScope()
            val snackbarHostState = remember { SnackbarHostState() }
            UserListTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(title = {
                            Text(text = "User List")
                        })
                    },
                    snackbarHost = {
                        SnackbarHost(hostState = snackbarHostState)
                    }
                ) { innerPadding ->
                    val navController = rememberNavController()
                    Column(Modifier.padding(innerPadding)) {
                        NavHost(
                            navController = navController,
                            startDestination = Screens.UsersListScreen.route
                        ) {
                            composable(Screens.UsersListScreen.route) {
                                UsersScreen(usersVM) {
                                    navController.navigate(Screens.UsersDetailScreen.route)
                                }
                            }

                            composable(Screens.UsersDetailScreen.route) {
                                UsersDetail(usersVM, snackBarEventHandler = {
                                    scope.launch {
                                        snackbarHostState.showSnackbar(it)
                                    }
                                })
                            }
                        }
                    }

                }
            }
        }
//        lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.STARTED) {
//                usersVM.userState.collectLatest {
//                    println(it.firstName)
//                }
//            }
//        }
    }
}

object Dimen {
    val BASE_8 = 8.dp
}
