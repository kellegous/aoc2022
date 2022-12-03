package kellegous.aoc

import java.io.IOException

object Input {
    private fun resourceOf(src: String) = this.javaClass.classLoader.getResource(src).let {
        if (it == null) {
            throw IOException("resource not found: $src")
        }
        it
    }

    fun readerFrom(src: String) = resourceOf(src).openStream().reader()

    fun textFrom(src: String) = resourceOf(src).readText()
}