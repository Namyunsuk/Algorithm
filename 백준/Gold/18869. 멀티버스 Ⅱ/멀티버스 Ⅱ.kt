val universes = mutableListOf<List<Int>>()
val newUniverses = mutableListOf<List<Int>>()

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val (m, n) = readLine().split(" ").map { it.toInt() }
    repeat(m) {
        universes.add(readLine().split(" ").map { it.toInt() })
    }

    universes.forEach { universe ->
        val newUniverse = mutableListOf<Int>()
        val refinedUniverse = universe.distinct().toMutableList()
        refinedUniverse.sort()

        universe.forEach { size ->
            newUniverse.add(refinedUniverse.binarySearch(size))
        }

        newUniverses.add(newUniverse)
    }

    var result = 0

    for (i in 0 until m) {
        for (j in i + 1 until m) {
            if (newUniverses[i] == newUniverses[j]) result++
        }
    }

    print(result)
}