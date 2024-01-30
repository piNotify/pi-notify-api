package dev.pilati.pinotify.api.util

data class GenericResponse(
    val error: Boolean,
    val error_code: String? = null,
    val message: String,
)