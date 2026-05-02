package org.chef.cooksense

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform