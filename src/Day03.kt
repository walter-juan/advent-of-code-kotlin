
fun main() {
    fun part1(input: List<String>): Int {
        val lowercaseRange = 'a'..'z'
        val uppercaseRange = 'A'..'Z'

        return input.sumOf { line ->
            val compartments = line.chunked(line.length / 2)
            val itemInBoth = compartments[0].toList().intersect(compartments[1].toList()).firstOrNull()
            if (lowercaseRange.contains(itemInBoth)) {
                lowercaseRange.indexOf(itemInBoth) + 1
            } else if (uppercaseRange.contains(itemInBoth)) {
                uppercaseRange.indexOf(itemInBoth) + 1 + 26
            } else {
                0
            }
        }
    }

    fun part2(input: List<String>): Int {
        return 1
    }

    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)
//    check(part2(testInput) == 1)

    val input = readInput("Day03")
    println(part1(input))
//    println(part2(input))
}