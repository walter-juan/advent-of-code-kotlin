
fun main() {
    fun part1(input: String): Int {
        val a = mutableListOf<Char>()
        var index = 0
        for(char in input) {
            index++
            a.add(char)

            if (a.size > 4) {
                a.removeAt(0)
            }

            if (a.size == 4 && a.size == a.distinct().size) {
                // found it!
                break
            }
        }

        return index
    }

    fun part2(input: List<String>): Int {
        return 1
    }

    val testInput = readInput("Day06_test")
    check(part1(testInput[0]) == 7)
    check(part1(testInput[1]) == 5)
    check(part1(testInput[2]) == 6)
    check(part1(testInput[3]) == 10)
    check(part1(testInput[4]) == 11)
    check(part2(testInput) == 1)

    val input = readInput("Day06")
    println(part1(input[0]))
    println(part2(input))
}