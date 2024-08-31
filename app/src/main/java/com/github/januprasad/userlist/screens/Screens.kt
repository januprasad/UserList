package com.github.januprasad.userlist.screens

sealed class Screens(val route: String) {
    object UsersListScreen : Screens("user_list")
    object  UsersDetailScreen : Screens("user_detail")
}