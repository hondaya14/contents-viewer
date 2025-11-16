package co.hondaya

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform