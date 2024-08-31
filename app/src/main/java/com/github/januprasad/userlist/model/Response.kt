package com.github.januprasad.userlist.model

data class Response(
    val limit: Int,
    val skip: Int,
    val total: Int,
    val users: List<User>
)