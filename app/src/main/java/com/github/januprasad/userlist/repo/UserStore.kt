package com.github.januprasad.userlist.repo

import com.github.januprasad.userlist.model.User

object UserStore{
    private lateinit var user: User
    fun storeUser(user: User){
        UserStore.user = user
    }
    fun getUser() = user
}