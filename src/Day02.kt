
enum class Shape(val score: Int) {
    ROCK(1) {
        override fun win(shape: Shape): Boolean = shape == SCISSORS
        override fun lose(shape: Shape): Boolean = shape == PAPER
        override fun draw(shape: Shape): Boolean = shape == ROCK
    },
    PAPER(2) {
        override fun win(shape: Shape): Boolean = shape == ROCK
        override fun lose(shape: Shape): Boolean = shape == SCISSORS
        override fun draw(shape: Shape): Boolean = shape == PAPER
    },
    SCISSORS(3) {
        override fun win(shape: Shape): Boolean = shape == PAPER
        override fun lose(shape: Shape): Boolean = shape == ROCK
        override fun draw(shape: Shape): Boolean = shape == SCISSORS
    };

    abstract fun win(shape: Shape): Boolean
    abstract fun lose(shape: Shape): Boolean
    abstract fun draw(shape: Shape): Boolean

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
        val scores = input.map { line ->
            val shapes = line.split(" ")
            val opponentShape = Shape.from(shapes[0])
            val roundEnd = shapes[1]

            if (roundEnd == "X") {
                Shape.values().first { it.lose(opponentShape) }.score
            } else if (roundEnd == "Y") {
                3 + Shape.values().first { it.draw(opponentShape) }.score
            } else {
                6 + Shape.values().first { it.win(opponentShape) }.score
            }
        }

        return scores.sum()
    }

    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}