
fun main() {
    fun part1(input: String): Int {
        val chars = mutableListOf<Char>()
        var index = 0
        val nDistinctChars = 4
        for(char in input) {
            index++
            chars.add(char)

            if (chars.size > nDistinctChars) {
                chars.removeAt(0)
            }

            if (chars.size == nDistinctChars && chars.size == chars.distinct().size) {
                // found it!
                break
            }
        }

        return index
    }

    fun part2(input: String): Int {
        val chars = mutableListOf<Char>()
        var index = 0
        val nDistinctChars = 14
        for(char in input) {
            index++
            chars.add(char)

            if (chars.size > nDistinctChars) {
                chars.removeAt(0)
            }

            if (chars.size == nDistinctChars && chars.size == chars.distinct().size) {
                // found it!
                break
            }
        }

        return index
    }

    val testInput = readInput("Day06_test")
    check(part1(testInput[0]) == 7)
    check(part1(testInput[1]) == 5)
    check(part1(testInput[2]) == 6)
    check(part1(testInput[3]) == 10)
    check(part1(testInput[4]) == 11)

    check(part2(testInput[0]) == 19)
    check(part2(testInput[1]) == 23)
    check(part2(testInput[2]) == 23)
    check(part2(testInput[3]) == 29)
    check(part2(testInput[4]) == 26)

    val input = readInput("Day06")
    println(part1(input[0]))
    println(part2(input[0]))
}