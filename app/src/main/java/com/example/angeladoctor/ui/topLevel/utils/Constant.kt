package com.example.angeladoctor.ui.topLevel.utils

object Constant {
    const val BASE_URL = "https://run.mocky.io/v3/"

    fun getGreetingMessage(): String {
        val hour = java.util.Calendar.getInstance().get(java.util.Calendar.HOUR_OF_DAY)
        return when (hour) {
            in 5..11 -> "Good Morning"
            in 12..16 -> "Good Afternoon"
            in 17..20 -> "Good Evening"
            else -> "Good Night"
        }
    }
}