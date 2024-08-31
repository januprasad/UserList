package com.github.januprasad.userlist.repo

import com.github.januprasad.userlist.model.Response

interface UsersRepository {
    suspend fun getUsers(): Response
}