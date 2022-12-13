
fun main() {
    fun buildCycles(input: List<String>): List<Int> {
        return input.map { instruction ->
            when {
                instruction == "noop" -> listOf(0)
                instruction.startsWith("addx") -> {
                    listOf(0,instruction.split(" ").last().toInt() )
                }
                else -> throw IllegalArgumentException("Invalid instruction [$instruction]")
            }
        }.flatten().mapIndexed { index, i ->
            if (index == 0) {
                1 + i
            } else {
                i
            }
        }
    }

    fun calcCycleAt(cycles: List<Int>, position: Int): Int {
        // take the position - 1 because is "during" the cycle
        return position * cycles.take(position - 1).sum()
    }

    fun part1(input: List<String>): Int {
        val cycles = buildCycles(input)
        return listOf(20, 60, 100, 140, 180, 220).sumOf { calcCycleAt(cycles, it) }
    }

    fun part2(input: List<String>): Int {
        return 1
    }

    val testInput = readInput("Day10_test")
    check(part1(testInput) == 13140)
    check(part2(testInput) == 1)

    val input = readInput("Day10")
    println(part1(input))
    println(part2(input))
}