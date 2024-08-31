package com.github.januprasad.userlist.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.januprasad.userlist.model.User
import com.github.januprasad.userlist.repo.UserStore
import com.github.januprasad.userlist.repo.UsersRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersVM @Inject constructor(private val usersRepoImpl: UsersRepositoryImpl) : ViewModel() {

    private var _uiState = MutableStateFlow<AppState>(AppState.Init)
    val uiState = _uiState.asStateFlow()


    private var _userState = MutableSharedFlow<User>()
    val userState = _userState.asSharedFlow()



    fun getUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update {
                AppState.Loading
            }
            val result = usersRepoImpl.getUsers()
            _uiState.update {
                AppState.Loaded(result.users)
            }
        }
    }

    fun storeUser(user: User) {
        UserStore.storeUser(user)
        viewModelScope.launch(Dispatchers.IO) {
            _userState.emit(user)
        }
    }
}

sealed class AppState {
    object Init : AppState()
    object Loading : AppState()
    data class Loaded(val users: List<User>) : AppState()
    data class Error(val message: String) : AppState()
}

sealed class UserState {
    object None : UserState()
    data class SelectedUser(val user: User) : UserState()
}