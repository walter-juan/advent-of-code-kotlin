
fun main() {
    fun part1(input: List<String>): Int {
        return input.map { line ->
            val ranges = line
                .split(",")
                .map { rangeStr ->
                    rangeStr.split("-").map { it.toInt() }
                }.map {
                    it[0]..it[1]
                }

            if ((ranges[0] - ranges[1]).isEmpty() || (ranges[1] - ranges[0]).isEmpty()) {
                1
            } else {
                0
            }
        }.sum()
    }

    fun part2(input: List<String>): Int {
        return input.map { line ->
            val ranges = line
                .split(",")
                .map { rangeStr ->
                    rangeStr.split("-").map { it.toInt() }
                }.map {
                    it[0]..it[1]
                }

            if (ranges[0].intersect(ranges[1]).isNotEmpty()) {
                1
            } else {
                0
            }
        }.sum()
    }

    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}