package dev.pilati.pinotify.api.util

data class GenericResponse(
    val error: Boolean,
    val errorCode: String? = null,
    val message: String,
)
