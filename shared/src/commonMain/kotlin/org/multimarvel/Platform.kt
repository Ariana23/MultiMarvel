package org.multimarvel

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform