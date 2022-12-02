fun main() {
    fun calcTotals(input: List<String>): List<Int> {
        val (_, totals) = input.fold(0 to listOf<Int>()) { acc, line ->
            val number = line.toIntOrNull()

            if (number != null) {
                (acc.first + number) to acc.second
            } else {
                0 to (acc.second + acc.first)
            }
        }

        return totals
    }

    fun part1(input: List<String>): Int {
        return calcTotals(input).max()
    }

    fun part2(input: List<String>): Int {
        return calcTotals(input).sortedDescending().take(3).sum()
    }

    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}