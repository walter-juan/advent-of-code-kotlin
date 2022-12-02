
enum class Shape(val score: Int) {
    ROCK(1) {
        override fun win(shape: Shape): Boolean = shape == SCISSORS
    },
    PAPER(2) {
        override fun win(shape: Shape): Boolean = shape == ROCK
    },
    SCISSORS(3) {
        override fun win(shape: Shape): Boolean = shape == PAPER
    };

    abstract fun win(shape: Shape): Boolean

    companion object {
        fun from(str: String) = if(listOf("A", "X").contains(str)) {
            ROCK
        } else if(listOf("B", "Y").contains(str)) {
            PAPER
        } else if(listOf("C", "Z").contains(str)) {
            SCISSORS
        } else {
            throw IllegalArgumentException("Invalid str [$str]")
        }
    }
}

fun main() {
    fun part1(input: List<String>): Int {
        val scores = input.map { line ->
            val shapes = line.split(" ")
            val opponentShape = Shape.from(shapes[0])
            val myShape = Shape.from(shapes[1])

            if (myShape == opponentShape) {
                myShape.score + 3
            } else if (myShape.win(opponentShape)) {
                myShape.score + 6
            } else {
                myShape.score
            }
        }

        return scores.sum()
    }

    fun part2(input: List<String>): Int {
        return 1
    }

    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 1)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}