var sensors = mutableListOf<Int>()
val differ = mutableListOf<Int>()

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val k = readLine().toInt()
    val input = readLine().split(" ").map { it.toInt() }

    sensors.addAll(input)
    sensors.sort()
    sensors = sensors.distinct().toMutableList()

    for (i in 1 until sensors.size) {
        differ.add(sensors[i] - sensors[i - 1])
    }

    differ.sortDescending()
    repeat(k - 1) {
        if (differ.isNotEmpty()) differ.removeFirst()
    }

    print(differ.sum())
}