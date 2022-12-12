import java.nio.file.Path
import java.nio.file.Paths

fun main() {
    fun processCommands(input: List<String>, foldersWithSizes: Map<Path, Int>, path: Path): Map<Path, Int> {
        val command = input.firstOrNull()

        return if (command != null) {
            when {
                command.startsWith("$ cd /") -> {
                    // switches the current directory to the outermost directory
                    processCommands(input = input.drop(1), foldersWithSizes = foldersWithSizes, path = path.root)
                }

                command.startsWith("$ cd ..") -> {
                    // moves out one level
                    processCommands(input = input.drop(1), foldersWithSizes = foldersWithSizes, path = path.parent)
                }

                command.startsWith("$ cd ") -> {
                    // moves in one level
                    val other = command.removePrefix("$ cd").trim()
                    processCommands(input = input.drop(1), foldersWithSizes = foldersWithSizes, path = path.resolve(other))
                }

                command.startsWith("$ ls") -> {
                    // list
                    val output = input.drop(1).takeWhile {
                        !it.startsWith("$ ")
                    }
                    val size = output
                        .filterNot { it.startsWith("dir") }
                        .sumOf { it.split(" ").first().toInt() }
                    val newFoldersWithSizes = foldersWithSizes.toMutableMap()
                    newFoldersWithSizes[path] = size

                    processCommands(input = input.drop(1 + output.size), foldersWithSizes = newFoldersWithSizes, path = path)
                }

                else -> throw IllegalArgumentException("command not supported [$command]")
            }
        } else {
            foldersWithSizes
        }
    }

    fun part1(input: List<String>): Int {
        val foldersWithSizes = processCommands(input = input, foldersWithSizes = mapOf(), path = Paths.get("/"))

        return foldersWithSizes
            .map { (path, _) ->
                // total folder size
                foldersWithSizes.filter { it != path && it.key.startsWith(path) }.map { it.value }.sum()
            }
            .filter { totalFolderSize ->
                totalFolderSize <= 100000
            }
            .sum()
    }

    fun part2(input: List<String>): Int {
        val foldersWithSizes = processCommands(input = input, foldersWithSizes = mapOf(), path = Paths.get("/"))

        val totalDiskSpace = 70000000
        val updateSpaceNeeded = 30000000
        val unusedSpace = totalDiskSpace - foldersWithSizes.map { (path, size) -> size }.sum()
        val minSpaceNeeded = updateSpaceNeeded - unusedSpace

        val folderSizes = foldersWithSizes
            .map { (path, _) ->
                // total folder size
                foldersWithSizes.filter { it != path && it.key.startsWith(path) }.map { it.value }.sum()
            }

        return folderSizes.fold(totalDiskSpace) { currentSize: Int, size: Int ->
            if (size in minSpaceNeeded..currentSize) {
                size
            } else {
                currentSize
            }
        }
    }

    val testInput = readInput("Day07_test")
    check(part1(testInput) == 95437)
    check(part2(testInput) == 24933642)

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}