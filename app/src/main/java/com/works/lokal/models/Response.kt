package com.works.lokal.models

import kotlinx.serialization.Serializable

@Serializable
data class Response(
    val body: String,
    val email: String,
    val id: Int,
    val name: String,
    val postId: Int
)