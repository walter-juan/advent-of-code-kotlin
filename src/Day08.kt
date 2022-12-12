
fun main() {
    fun inputToTreeSizesMap(input: List<String>): List<List<Int>> {
        return input.map { line ->
            line.toCharArray().map { it.toString().toInt()}
        }
    }

    fun List<List<Int>>.toHiddenTrees(): List<List<Boolean>> {
        val rowValues = this
        return rowValues.mapIndexed { rowIndex, colValues ->
            colValues.mapIndexed { colIndex, treeSize ->
               if (rowIndex == 0 || colIndex == 0) {
                   false
               } else if (rowIndex == (rowValues.size - 1) || colIndex == (colValues.size - 1) ) {
                   false
               } else {
                   this[rowIndex].take(colIndex).any { it >= treeSize } &&
                   this[rowIndex].drop(colIndex + 1).any { it >= treeSize } &&
                   this.map { it[colIndex] }.take(rowIndex).any { it >= treeSize } &&
                   this.map { it[colIndex] }.drop(rowIndex + 1).any { it >= treeSize }
               }
            }
        }
    }

    fun part1(input: List<String>): Int {
        val treeSizesMap = inputToTreeSizesMap(input)
        val treeVisibleMap = treeSizesMap.toHiddenTrees()
        return treeVisibleMap.flatten().filter { !it }.size
    }

    fun part2(input: List<String>): Int {
        return 1
    }

    val testInput = readInput("Day08_test")
    check(part1(testInput) == 21)
    check(part2(testInput) == 1)

    val input = readInput("Day08")
    println(part1(input))
    println(part2(input))
}