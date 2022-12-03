package kellegous.aoc

object Input {
    fun readerFrom(src: String) = this.javaClass.classLoader.getResourceAsStream(src)!!.reader()

    fun textFrom(src: String) = this.javaClass.classLoader.getResource(src)!!
}