package com.github.januprasad.userlist.model

import com.github.januprasad.userlist.model.Address

data class Company(
    val address: Address,
    val department: String,
    val name: String,
    val title: String
)