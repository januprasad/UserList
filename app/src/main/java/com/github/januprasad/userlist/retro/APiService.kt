package com.github.januprasad.userlist.retro

import com.github.januprasad.userlist.model.Response
import retrofit2.http.GET

interface APiService {
    @GET("users")
    suspend fun getUsers(): Response
}