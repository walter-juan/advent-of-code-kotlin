import java.util.*

fun main() {
    fun buildCrateStacks(input: List<String>): Map<Int, LinkedList<String>> {
        val stackLines = input.takeWhile {it ->
            it.isNotEmpty()
        }.dropLast(1)

        val numberOfStacks = input.takeWhile { it ->
            it.isNotEmpty()
        }.last().split(" ").last { it.isNotBlank() }.toInt()

        val crateStacks = buildMap<Int, LinkedList<String>> {
            repeat(numberOfStacks) {
                put((it + 1), LinkedList())
            }
        }

        stackLines.forEach { line ->
            var tmpLine = line
            var nStack = 0
            while (tmpLine.isNotEmpty()) {
                nStack += 1
                val crate = tmpLine.take(3).drop(1).take(1)
                tmpLine = tmpLine.drop(4)
                if (crate.isNotBlank()) {
                    crateStacks[nStack]?.push(crate)
                }
            }
        }

        return crateStacks
    }

    fun part1(input: List<String>): String {
        val crateStacks = buildCrateStacks(input)

        val movementLines = input.takeLastWhile {it ->
            it.isNotEmpty()
        }

        movementLines.forEach { movementLine ->
            val a = movementLine.split(" ")
            val size = a[1].toInt()
            val from = a[3].toInt()
            val to = a[5].toInt()
            repeat(size) {
                val crate = crateStacks[from]?.removeLast()!!
                crateStacks[to]?.add(crate)
            }
        }

        return crateStacks.map { (key, value) ->
            value.last()
        }.joinToString(separator = "")
    }

    fun part2(input: List<String>): String {
        val crateStacks = buildCrateStacks(input)

        val movementLines = input.takeLastWhile {it ->
            it.isNotEmpty()
        }

        movementLines.forEach { movementLine ->
            val a = movementLine.split(" ")
            val size = a[1].toInt()
            val from = a[3].toInt()
            val to = a[5].toInt()

            val crate = crateStacks[from]?.takeLast(size) ?: listOf()
            crateStacks[to]?.addAll(crate)
            repeat(size) { crateStacks[from]?.removeLastOrNull() }
        }

        return crateStacks.map { (key, value) ->
            value.last()
        }.joinToString(separator = "")
    }

    val testInput = readInput("Day05_test")
    check(part1(testInput) == "CMZ")
    check(part2(testInput) == "MCD")

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}