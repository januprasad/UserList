package com.github.januprasad.userlist.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.github.januprasad.userlist.repo.UserStore
import com.github.januprasad.userlist.vm.UsersVM
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun UsersDetail(viewModel: UsersVM) {
    val lifecycle = LocalLifecycleOwner.current
    LaunchedEffect(Unit) {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            launch {
                viewModel.userState.collectLatest {
                   println(it.firstName + " " + it.lastName)
                }
            }
        }
    }
    UserRow(user = UserStore.getUser())
}
