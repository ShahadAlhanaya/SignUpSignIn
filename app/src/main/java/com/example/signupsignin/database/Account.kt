package com.example.signupsignin.database

import java.io.Serializable


data class Account(
    val id: Long? = null,
    val username: String? = null,
    val mobile: String? = null,
    val location: String? = null,
    val password: String? = null
) : Serializable
