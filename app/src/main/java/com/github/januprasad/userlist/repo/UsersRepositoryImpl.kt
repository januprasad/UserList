package com.github.januprasad.userlist.repo

import com.github.januprasad.userlist.model.Response
import com.github.januprasad.userlist.repo.UsersRepository
import com.github.januprasad.userlist.retro.APiService
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(private val repo: APiService): UsersRepository
{
    override suspend fun getUsers(): Response {
      return repo.getUsers()
    }
}