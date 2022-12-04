
fun main() {
    fun part1(input: List<String>): Int {
        return input.map { line ->
            val rangesStr = line.split(",")
            val firstRangeStr = rangesStr[0].split("-")
            val firstRange = firstRangeStr[0].toInt()..firstRangeStr[1].toInt()
            val secondRangeStr = rangesStr[1].split("-")
            val secondRange = secondRangeStr[0].toInt()..secondRangeStr[1].toInt()

            if ((firstRange - secondRange).isEmpty() || (secondRange - firstRange).isEmpty()) {
                1
            } else {
                0
            }
        }.sum()
    }

    fun part2(input: List<String>): Int {
        return input.map { line ->
            val rangesStr = line.split(",")
            val firstRangeStr = rangesStr[0].split("-")
            val firstRange = firstRangeStr[0].toInt()..firstRangeStr[1].toInt()
            val secondRangeStr = rangesStr[1].split("-")
            val secondRange = secondRangeStr[0].toInt()..secondRangeStr[1].toInt()

            if ((firstRange - secondRange).size != firstRange.toList().size || (secondRange - firstRange).size != secondRange.toList().size) {
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